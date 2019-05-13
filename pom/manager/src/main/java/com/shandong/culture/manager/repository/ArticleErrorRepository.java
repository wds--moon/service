package com.shandong.culture.manager.repository;

import com.shandong.culture.manager.entity.ArticleError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ArticleErrorRepository extends JpaRepository<ArticleError, Long> , QuerydslPredicateExecutor<ArticleError> {
}
