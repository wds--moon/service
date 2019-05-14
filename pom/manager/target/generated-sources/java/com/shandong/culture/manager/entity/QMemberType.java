package com.shandong.culture.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberType is a Querydsl query type for MemberType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberType extends EntityPathBase<MemberType> {

    private static final long serialVersionUID = 1918249507L;

    public static final QMemberType memberType = new QMemberType("memberType");

    public final StringPath name = createString("name");

    public QMemberType(String variable) {
        super(MemberType.class, forVariable(variable));
    }

    public QMemberType(Path<? extends MemberType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberType(PathMetadata metadata) {
        super(MemberType.class, metadata);
    }

}

