package com.shandong.culture.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.shandong.culture.manager.converter.MemberConverter;
import com.shandong.culture.manager.dto.MemberDto;
import com.shandong.culture.manager.entity.Member;
import com.shandong.culture.manager.entity.QMember;
import com.shandong.culture.manager.repository.MemberReopsiotry;
import com.shandong.culture.manager.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberReopsiotry memberReopsiotry;

	@Autowired
	private MemberConverter memberConverter;

	private final QMember qMember = QMember.member;

	@Override
	@Transactional
	public Member save(MemberDto data) {
		Member member = new Member();
		return memberReopsiotry.save(memberConverter.copyProperties(member, data));
	}

	@Override
	public Member findById(Long id) {
		return memberReopsiotry.findById(id).orElse(null);
	}

	@Override
	public Page<Member> find(String keywords, Pageable pageable) {
		BooleanBuilder query = new BooleanBuilder();
		if (StringUtils.isNotBlank(keywords)) {
			query.and(qMember.username.containsIgnoreCase(keywords)
					.or(qMember.fullname.containsIgnoreCase(keywords))
					.or(qMember.email.containsIgnoreCase(keywords)));
		}
		return memberReopsiotry.findAll(query, pageable);
	}
}
