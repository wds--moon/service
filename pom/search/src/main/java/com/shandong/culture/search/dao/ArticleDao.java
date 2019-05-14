package com.shandong.culture.search.dao;

import com.shandong.culture.search.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
* @Description:    springframework.data.elasticsearch 管理es的增删改查主要是实体注解一起使用,简洁代码
* @Author:         moon
* @CreateDate:     2019/5/14 0014 09:50
* @UpdateUser:     moon
* @UpdateDate:     2019/5/14 0014 09:50
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface ArticleDao  extends ElasticsearchRepository<Article, Long> {

}
