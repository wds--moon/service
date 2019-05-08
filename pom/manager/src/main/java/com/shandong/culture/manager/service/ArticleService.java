package com.shandong.culture.manager.service;

import com.shandong.culture.manager.entity.Article;

/**
 * @author Administrator
 * @Title: Artilcle
 * @ProjectName culture_super
 * @Description: TODO
 * @date 2019/5/7 000715:58
 */
public interface ArticleService {


    Article findArticleById(Article article);

    void saveArticle(Article article);
}
