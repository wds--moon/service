package com.shandong.culture.manager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shandong.culture.manager.dto.MemberTypeDto;
import com.shandong.culture.manager.entity.MemberType;
import com.shandong.culture.manager.repository.MemberTypeRepository;
import com.shandong.culture.manager.service.MemberTypeService;

@Service
public class MemberTypeServiceImpl implements MemberTypeService {

	@Autowired
	private MemberTypeRepository memberTypeRepository;

	@Override
	public boolean exist() {
		return memberTypeRepository.count() > 0;
	}

	@Override
	@Transactional
	public MemberType save(MemberTypeDto data) {
		MemberType memberType = new MemberType(data.getName());
		return memberTypeRepository.save(memberType);
	}

	@Override
	@Transactional
	public List<MemberType> save(List<MemberTypeDto> datas) {
		List<MemberType> types = datas.stream().map(MemberTypeDto::getName)
				.map(MemberType::new)
				.collect(Collectors.toList());
		return memberTypeRepository.saveAll(types);
	}

}
