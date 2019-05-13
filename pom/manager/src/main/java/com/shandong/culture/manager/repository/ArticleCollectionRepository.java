package com.shandong.culture.manager.repository;

import com.shandong.culture.manager.entity.ArticleCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ArticleCollectionRepository extends JpaRepository<ArticleCollection, Long>, QuerydslPredicateExecutor<ArticleCollection> {
}
