package com.shandong.culture.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.shandong.culture.manager.entity.Member;

public interface MemberReopsiotry extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

}
