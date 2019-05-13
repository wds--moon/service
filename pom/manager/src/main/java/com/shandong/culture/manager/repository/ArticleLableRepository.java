package com.shandong.culture.manager.repository;

import com.shandong.culture.manager.entity.ArticleLable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ArticleLableRepository extends JpaRepository<ArticleLable, Long> , QuerydslPredicateExecutor<ArticleLable> {
}
