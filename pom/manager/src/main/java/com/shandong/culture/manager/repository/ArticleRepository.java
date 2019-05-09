package com.shandong.culture.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shandong.culture.manager.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
