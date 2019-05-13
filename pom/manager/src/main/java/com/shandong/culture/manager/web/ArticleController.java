package com.shandong.culture.manager.web;

import com.shandong.culture.manager.entity.Article;
import com.shandong.culture.manager.service.ArticleService;
import io.swagger.annotations.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moon
 * @create 2019-05-07 15:57
 **/
@Api("文章")
@RestController
@RequestMapping("articles")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	// articles/1
	// articles/1?ab=123
	@GetMapping("{id}")
	public Article findById(@PathVariable("id") Long id) {
		Article article = new Article();
		article.setId(13L);
		return articleService.findArticleById(article);
	}

	// articles?draw=124
	@GetMapping(params = { "draw","!keywords","name!==admin" },headers = {},consumes = {})
	public List<Article> find(@RequestParam("draw") Long draw) {
		return null;
	}

	@GetMapping
	public List<Article> find2(@RequestParam("keywords") String keywords) {
		return null;
	}

	@PostMapping
	public Article save() {
		Article article = new Article();
		article.setId(14L);
		return articleService.saveArticle(article);
	}
}
