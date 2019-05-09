package com.shandong.culture.manager.converter;

import org.springframework.stereotype.Component;

import com.dm.common.converter.AbstractConverter;
import com.shandong.culture.manager.dto.MemberDto;
import com.shandong.culture.manager.entity.Member;

@Component
public class MemberConverter extends AbstractConverter<Member, MemberDto> {

	@Override
	protected MemberDto toDtoActual(Member model) {
		MemberDto dto = new MemberDto();
		dto.setEmail(model.getEmail());
		dto.setId(model.getId());
		dto.setMobile(model.getMobile());
		dto.setUsername(model.getUsername());
		dto.setPassword(model.getPassword());
		return dto;
	}

	@Override
	public Member copyProperties(Member model, MemberDto dto) {
		model.setEmail(dto.getEmail());
		model.setMobile(dto.getMobile());
		model.setUsername(dto.getUsername());
		return model;
	}

}
