package com.shandong.culture.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleCollection is a Querydsl query type for ArticleCollection
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticleCollection extends EntityPathBase<ArticleCollection> {

    private static final long serialVersionUID = 1796980773L;

    public static final QArticleCollection articleCollection = new QArticleCollection("articleCollection");

    public final NumberPath<Long> articleId = createNumber("articleId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QArticleCollection(String variable) {
        super(ArticleCollection.class, forVariable(variable));
    }

    public QArticleCollection(Path<? extends ArticleCollection> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleCollection(PathMetadata metadata) {
        super(ArticleCollection.class, metadata);
    }

}

