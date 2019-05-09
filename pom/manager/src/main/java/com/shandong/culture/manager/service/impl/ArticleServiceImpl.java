package com.shandong.culture.manager.service.impl;

import com.shandong.culture.manager.entity.Article;
//import com.shandong.culture.manager.mapper.ArticleMapper;
import com.shandong.culture.manager.repository.ArticleRepository;
import com.shandong.culture.manager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author moon
 * @create 2019-05-07 16:00
 **/
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleRepository articleMapper;

	@Override
	public Article findArticleById(Article article) {
		return articleMapper.findById(13L).orElse(null);
	}

	@Override
	public Article saveArticle(Article article) {
		return articleMapper.save(article);
	}
}
