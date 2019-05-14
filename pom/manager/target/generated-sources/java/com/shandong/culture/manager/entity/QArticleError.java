package com.shandong.culture.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleError is a Querydsl query type for ArticleError
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticleError extends EntityPathBase<ArticleError> {

    private static final long serialVersionUID = 832415777L;

    public static final QArticleError articleError = new QArticleError("articleError");

    public final NumberPath<Long> articleId = createNumber("articleId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> dealId = createNumber("dealId", Integer.class);

    public final StringPath dealResult = createString("dealResult");

    public final NumberPath<Integer> dealStatus = createNumber("dealStatus", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> dealTime = createDateTime("dealTime", java.time.LocalDateTime.class);

    public final StringPath errorContent = createString("errorContent");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QArticleError(String variable) {
        super(ArticleError.class, forVariable(variable));
    }

    public QArticleError(Path<? extends ArticleError> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleError(PathMetadata metadata) {
        super(ArticleError.class, metadata);
    }

}

