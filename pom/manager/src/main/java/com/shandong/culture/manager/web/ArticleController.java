package com.shandong.culture.manager.web;

import com.shandong.culture.manager.entity.Article;
import com.shandong.culture.manager.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon
 * @create 2019-05-07 15:57
 **/
@Api("文章")
@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;


    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Article findById() {
        Article article = new Article();
        article.setId(13L);
        return articleService.findArticleById(article);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save() {
        Article article = new Article();
        article.setId(13L);
        articleService.saveArticle(article);
    }
}
