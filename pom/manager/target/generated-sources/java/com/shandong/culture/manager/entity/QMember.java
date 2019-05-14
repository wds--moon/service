package com.shandong.culture.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 182121289L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.dm.uap.entity.QUser _super = new com.dm.uap.entity.QUser(this);

    //inherited
    public final BooleanPath accountExpired = _super.accountExpired;

    //inherited
    public final BooleanPath credentialsExpired = _super.credentialsExpired;

    //inherited
    public final StringPath description = _super.description;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    //inherited
    public final StringPath fullname = _super.fullname;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath locked = _super.locked;

    //inherited
    public final StringPath mobile = _super.mobile;

    //inherited
    public final NumberPath<Long> order = _super.order;

    //inherited
    public final MapPath<com.dm.uap.entity.Department, Long, NumberPath<Long>> orders = _super.orders;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final MapPath<com.dm.uap.entity.Department, String, StringPath> posts = _super.posts;

    //inherited
    public final StringPath regionCode = _super.regionCode;

    //inherited
    public final ListPath<com.dm.uap.entity.Role, com.dm.uap.entity.QRole> roles = _super.roles;

    //inherited
    public final StringPath scenicName = _super.scenicName;

    public final QMemberType type;

    //inherited
    public final StringPath username = _super.username;

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.type = inits.isInitialized("type") ? new QMemberType(forProperty("type")) : null;
    }

}

