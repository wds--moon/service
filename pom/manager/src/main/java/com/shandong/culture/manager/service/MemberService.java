package com.shandong.culture.manager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shandong.culture.manager.dto.MemberDto;
import com.shandong.culture.manager.entity.Member;

public interface MemberService {

	public Member save(MemberDto member);

	public Member findById(Long id);

	public Page<Member> find(String keywords, Pageable pageable);

	/**
	 * 检测指定用户名是否被占用
	 * 
	 * @param username
	 * @return
	 */
	public Boolean existByUsername(String username);

}
