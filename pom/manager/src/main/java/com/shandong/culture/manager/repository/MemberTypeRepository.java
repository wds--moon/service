package com.shandong.culture.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shandong.culture.manager.entity.MemberType;

public interface MemberTypeRepository extends JpaRepository<MemberType, String> {

}
