package com.shandong.culture.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.shandong.culture.search.common.constant.EsEnum;
import com.shandong.culture.search.common.util.ChineseCharacterUtil;
import com.shandong.culture.search.entity.Article;
import com.shandong.culture.search.entity.ArticleVersion;
import com.shandong.culture.search.formvo.ArticleForm;
import com.shandong.culture.search.formvo.ArticleSearchFrom;
import com.shandong.culture.search.mapper.ArticleMapper;
import com.shandong.culture.search.mapper.ArticleVersionMapper;
import com.shandong.culture.search.model.ResponseVO;
import com.shandong.culture.search.service.ArticleService;
import com.shandong.culture.search.service.ElasticSearchService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.exception.SqlParseException;
import org.nlpcn.es4sql.query.SqlElasticSearchRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLFeatureNotSupportedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    ArticleVersionMapper articleVersionMapper;
    @Autowired
    private TransportClient transportClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Article article) {
        String pingyin = ChineseCharacterUtil.convertHanzi2Pinyin(article.getResourceNumber() + article.getGeographicalLocation(), false).toUpperCase();
        getVersionIndex(article, pingyin);
        articleMapper.insert(article);
        elasticSearchService.insertById(EsEnum.INDEX.getValue(), EsEnum.TYPE.getValue(), String.valueOf(article.getId()), JSON.toJSONString(article));

    }

    /**
     * 获取前缀编号最大的自增id,并且修改当前前缀的version
     *
     * @param article
     * @param pingyin
     */
    private void getVersionIndex(Article article, String pingyin) {
        ArticleVersion version = new ArticleVersion();
        version.setResourceNum(pingyin);
        ArticleVersion resVersion = articleVersionMapper.selectOne(version);
        if (resVersion != null) {
            Integer incc = resVersion.getVersion() + 1;
            article.setResourceNumber(pingyin + incc);
            resVersion.setVersion(incc);
            articleVersionMapper.updateByPrimaryKey(resVersion);
        } else {
            version.setVersion(1);
            article.setResourceNumber(pingyin + 1);
            articleVersionMapper.insert(version);

        }
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
        String pingyin = ChineseCharacterUtil.convertHanzi2Pinyin(article.getResourceNumber() + article.getGeographicalLocation(), false).toLowerCase();
        getVersionIndex(article, pingyin);
        articleMapper.updateByPrimaryKey(article);
        elasticSearchService.updateById(EsEnum.INDEX.getValue(), EsEnum.TYPE.getValue(), String.valueOf(article.getId()), JSON.toJSONString(article));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        Article info = new Article();
        info.setId(Long.parseLong(id));
        articleMapper.delete(info);
        elasticSearchService.deleteById(EsEnum.INDEX.getValue(), EsEnum.TYPE.getValue(), id);
    }

    @Override
    public  Page<Article> matchQueryArticle(ArticleForm article) {

        StringBuilder sql = new StringBuilder("select * from ").append(EsEnum.INDEX.getValue());
        sql.append(" where  1=1 ");
        /**
         * 如果存在资源分类,那么必须指定为死值,固定有一个分类匹配
         */
        if (StringUtils.isNotEmpty(article.getResourceClassification())) {
            sql.append(" and resourceName = '").append(article.getResourceClassification()).append("'");
        }

        if (StringUtils.isNotEmpty(article.getResourceName())) {
            sql.append(" and (resourceName.ngram = matchQuery('").append(article.getResourceName()).append("')");
            sql.append(" or resourceName.SPY = matchQuery('").append(article.getResourceName()).append("')");
            sql.append(" or resourceName.FPY = matchQuery('").append(article.getResourceName()).append("'))");
        }
        if (StringUtils.isNotEmpty(article.getContext())) {
            sql.append(" and  context = matchQuery('").append(article.getContext()).append("')");
        }
        if (StringUtils.isNotEmpty(article.getResourceLable())) {
            sql.append(" and (resourceLable.ngram = matchQuery('").append(article.getResourceName()).append("')");
            sql.append(" or resourceLable.SPY = matchQuery('").append(article.getResourceName()).append("')");
            sql.append(" or resourceLable.FPY = matchQuery('").append(article.getResourceName()).append("'))");
        }
        sql.append(" limit ").append(article.getPageNum()).append(" , ").append(article.getPageSize());
        SearchHits response = null;
        try {
            response = query(sql.toString());
        } catch (Exception e) {
            return null;
        }
        Pageable pageable = PageRequest.of(article.getPageNum(), article.getPageSize());
        PageImpl page = new PageImpl(setListMap(response), pageable, response.totalHits);
        return page;
    }

    @Override
    public  Page<Article> searchArticle(ArticleSearchFrom article) {
        StringBuilder sql = new StringBuilder("select /*! HIGHLIGHT(resourceName,pre_tags : ['<b>'], post_tags : ['</b>']  ) */ /*! HIGHLIGHT(context,pre_tags : ['<b>'], post_tags : ['</b>']  ) */ * from ").append(EsEnum.INDEX.getValue());
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
            sql.append(" and (resourceName.ngram = score(matchQuery('").append(article.getKeyword()).append("'),100)");
            sql.append(" or resourceName.SPY = score(matchQuery('").append(article.getKeyword()).append("'),100)");
            sql.append(" or resourceName.FPY = score(matchQuery('").append(article.getKeyword()).append("'),100)");
            sql.append(" or  resourceLable.ngram = score(matchQuery('").append(article.getKeyword()).append("'),50)");
            sql.append(" or  resourceLable.SPY = score(matchQuery('").append(article.getKeyword()).append("'),50)");
            sql.append(" or  resourceLable.FPY = score(matchQuery('").append(article.getKeyword()).append("'),50)");
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
        Pageable pageable = PageRequest.of(article.getPageNum(), article.getPageSize());
        PageImpl page = new PageImpl(setListMap(response), pageable, response.totalHits);
        return page;
    }

    private List<Map<String, Object>> setListMap(SearchHits response) {

        List<Map<String, Object>> data = new ArrayList<>();
        Map<String,Object> map=null;
        for (SearchHit hit : response.getHits()) {
            map=new HashMap<>();
            map.put("id",hit.getId());
            HighlightField centext = hit.getHighlightFields().get("context");
            HighlightField resourceName = hit.getHighlightFields().get("resourceName");
            if(centext!=null&&centext.getFragments()!=null){
                map.put("context",centext.getFragments()[0].toString());
            }else{
                map.put("context",hit.getSourceAsMap().get("context"));
            }
            if(resourceName!=null&&resourceName.getFragments()!=null){
                map.put("resourceName",resourceName.getFragments()[0].toString());
            }else{
                map.put("resourceName",hit.getSourceAsMap().get("resourceName"));
            }
            data.add(map);
        }
        return data;
    }

    private SearchHits query(String query) throws SqlParseException, SQLFeatureNotSupportedException {
        SearchDao searchDao = new SearchDao(transportClient);
        SqlElasticSearchRequestBuilder select = (SqlElasticSearchRequestBuilder) searchDao.explain(query).explain();
        return ((SearchResponse) select.get()).getHits();
    }

}
