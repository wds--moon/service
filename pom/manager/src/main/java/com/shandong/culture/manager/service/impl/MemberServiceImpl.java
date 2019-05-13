package com.shandong.culture.manager.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dm.uap.converter.UserConverter;
import com.dm.uap.entity.Role;
import com.dm.uap.repository.RoleRepository;
import com.dm.uap.repository.UserRepository;
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
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MemberConverter memberConverter;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final QMember qMember = QMember.member;

	@Override
	@Transactional
	public Member save(MemberDto data) {
		Member member = new Member();
		member.setPassword(passwordEncoder.encode(data.getPassword()));
		Optional<Role> roleOptional = roleRepository.findByName("ROLE_USER");
		// 设置用户为的默认角色
		if (roleOptional.isPresent()) {
			member.setRoles(Collections.singletonList(roleOptional.get()));
		}
		return memberReopsiotry.save(memberConverter.copyProperties(member, data));
	}

	public UserDetails loadMemberByUsername(String username) {
		Optional<Member> member = memberReopsiotry.findByUsername(username);
		return userConverter.toUserDetailsDto(member);
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

	@Override
	public Boolean existByUsername(String username) {
		return userRepository.findOneByUsernameIgnoreCase(username).isPresent();
	}

}
