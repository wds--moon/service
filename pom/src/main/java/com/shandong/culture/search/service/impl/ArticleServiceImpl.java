package com.shandong.culture.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.shandong.culture.search.common.constant.EsEnum;
import com.shandong.culture.search.entity.Article;
import com.shandong.culture.search.formvo.ArticleForm;
import com.shandong.culture.search.formvo.ArticleSearchFrom;
import com.shandong.culture.search.mapper.ArticleMapper;
import com.shandong.culture.search.model.ResponseVO;
import com.shandong.culture.search.service.ArticleService;
import com.shandong.culture.search.service.ElasticSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.SqlElasticSearchRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLFeatureNotSupportedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author moon
 * @create 2019-05-05 14:51
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ElasticSearchService elasticSearchService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TransportClient transportClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Article article) {
        articleMapper.insert(article);
        elasticSearchService.insertById(EsEnum.INDEX.getValue(), EsEnum.TYPE.getValue(), String.valueOf(article.getId()), JSON.toJSONString(article));

    }


    /**
     * 数据转换,专门用于新增,编辑
     *
     * @param article
     * @return
     */
    private Article getArticle(ArticleForm article) {
        Article info = new Article();
        info.setResourceClassification(article.getResourceClassification());
        info.setResourceNumber(article.getResourceNumber());
        info.setResourceType(article.getResourceType());
        info.setResourceName(article.getResourceName());
        info.setResourceLable(article.getResourceLable());
        info.setGeographicalLocation(article.getGeographicalLocation());
        info.setResourceQuantity(article.getResourceQuantity());
        info.setRegion(article.getRegion());
        info.setYears(article.getYears());
        info.setAreaCovered(article.getAreaCovered());
        info.setGrade(article.getGrade());
        info.setCreateTime(LocalDateTime.now());
        info.setLastUpdate(LocalDateTime.now());
        info.setAttUrl(article.getAttUrl());
        info.setImgUrl(article.getImgUrl());
        info.setContext(article.getContext());
        return info;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Article article) {
        articleMapper.updateByPrimaryKey(article);
        elasticSearchService.updateById(EsEnum.INDEX.getValue(), EsEnum.TYPE.getValue(), String.valueOf(article.getId()), JSON.toJSONString(article));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        articleMapper.deleteByPrimaryKey(id);
        elasticSearchService.deleteById(EsEnum.INDEX.getValue(), EsEnum.TYPE.getValue(), id);
    }


    @Override
    public ResponseVO findArticle(ArticleForm article) {
        StringBuilder sql = new StringBuilder("select * from ").append(EsEnum.INDEX.getValue());
        sql.append(" where  1=1 ");
        if (StringUtils.isNotEmpty(article.getResourceName())) {
            sql.append(" and title ='").append(article.getResourceName()).append("'");
        }
        sql.append(" limit ").append(article.getPageNum()).append(" , ").append(article.getPageSize());
        SearchHits response = null;
        try {
            response = query(sql.toString());
        } catch (Exception e) {
            return null;
        }
        return ResponseVO.success(setListMap(response), article.getPageNum(), article.getPageSize(), response.totalHits);
    }

    @Override
    public ResponseVO matchQueryArticle(ArticleForm article) {

        StringBuilder sql = new StringBuilder("select * from ").append(EsEnum.INDEX.getValue());
        sql.append(" where  1=1 ");
        /**
         * 如果存在资源分类,那么必须指定为死值,固定有一个分类匹配
         */
        if (StringUtils.isNotEmpty(article.getResourceClassification())) {
            sql.append(" and resourceName = '").append(article.getResourceClassification()).append("'");
        }

        if (StringUtils.isNotEmpty(article.getResourceName())) {
            sql.append(" and resourceName = matchQuery('").append(article.getResourceName()).append("')");
        }
        if (StringUtils.isNotEmpty(article.getContext())) {
            sql.append(" and  context = matchQuery('").append(article.getContext()).append("')");
        }
        if (StringUtils.isNotEmpty(article.getResourceLable())) {
            sql.append(" and  resourceLable = matchQuery('").append(article.getResourceLable()).append("')");
        }
        sql.append(" limit ").append(article.getPageNum()).append(" , ").append(article.getPageSize());
        SearchHits response = null;
        try {
            response = query(sql.toString());
        } catch (Exception e) {
            return null;
        }
        return ResponseVO.success(setListMap(response), article.getPageNum(), article.getPageSize(), response.totalHits);
    }

    @Override
    public ResponseVO searchArticle(ArticleSearchFrom article) {
        StringBuilder sql = new StringBuilder("select * from ").append(EsEnum.INDEX.getValue());
        sql.append(" where  1=1 ");
        /**
         * 如果存在资源分类,那么必须指定为死值,固定有一个分类匹配
         */
        if (StringUtils.isNotEmpty(article.getResourceClassification())) {
            sql.append(" and resourceName = '").append(article.getResourceClassification()).append("'");
        }
        /**
         * 检索关键字按照名称,标签,内容,进行全文匹配
         */
        if (StringUtils.isNotEmpty(article.getKeyword())) {
            sql.append(" and (resourceName = score(matchQuery('").append(article.getKeyword()).append("'),100)");
            sql.append(" or  resourceLable = score(matchQuery('").append(article.getKeyword()).append("'),50)");
            sql.append(" or  geographicalLocation = score(matchQuery('").append(article.getKeyword()).append("'),10)");
            sql.append(" or  region = score(matchQuery('").append(article.getKeyword()).append("'),10)");
            sql.append(" or  years = score(matchQuery('").append(article.getKeyword()).append("'),10)");
            sql.append(" or  areaCovered = score(matchQuery('").append(article.getKeyword()).append("'),10)");
            sql.append(" or  grade = score(matchQuery('").append(article.getKeyword()).append("'),10)");
            sql.append(" or  context = score(matchQuery('").append(article.getKeyword()).append("'),1))");
        }
        sql.append(" order by _score desc  ");
        sql.append(" limit ").append(article.getPageNum()).append(" , ").append(article.getPageSize());
        SearchHits response = null;
        try {
            response = query(sql.toString());
        } catch (Exception e) {
            return null;
        }
        return ResponseVO.success(setListMap(response), article.getPageNum(), article.getPageSize(), response.totalHits);
    }

    private List<Map<String, Object>> setListMap(SearchHits response) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (SearchHit hit : response.getHits()) {
            data.add(hit.getSourceAsMap());
        }
        return data;
    }

    private SearchHits query(String query) throws SqlParseException, SQLFeatureNotSupportedException {
        SearchDao searchDao = new SearchDao(transportClient);
        SqlElasticSearchRequestBuilder select = (SqlElasticSearchRequestBuilder) searchDao.explain(query).explain();
        return ((SearchResponse) select.get()).getHits();
    }

}
