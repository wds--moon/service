package com.shandong.culture.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleLable is a Querydsl query type for ArticleLable
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticleLable extends EntityPathBase<ArticleLable> {

    private static final long serialVersionUID = 838358495L;

    public static final QArticleLable articleLable = new QArticleLable("articleLable");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> special = createNumber("special", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QArticleLable(String variable) {
        super(ArticleLable.class, forVariable(variable));
    }

    public QArticleLable(Path<? extends ArticleLable> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleLable(PathMetadata metadata) {
        super(ArticleLable.class, metadata);
    }

}

