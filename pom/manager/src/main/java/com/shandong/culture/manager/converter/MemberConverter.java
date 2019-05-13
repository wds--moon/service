package com.shandong.culture.manager.converter;

import java.util.Objects;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.dm.common.converter.AbstractConverter;
import com.shandong.culture.manager.dto.MemberDto;
import com.shandong.culture.manager.dto.RegistryDto;
import com.shandong.culture.manager.entity.Member;
import com.shandong.culture.manager.entity.MemberType;

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
		if (!Objects.isNull(model.getType())) {
			dto.setType(model.getType().getName());
		}
		return dto;
	}

	@Override
	public Member copyProperties(Member model, MemberDto dto) {
		model.setEmail(dto.getEmail());
		model.setMobile(dto.getMobile());
		model.setUsername(dto.getUsername());
		if (StringUtils.isNotBlank(dto.getType())) {
			model.setType(new MemberType(dto.getType()));
		}
		return model;
	}

	public MemberDto toDto(@Valid RegistryDto data) {
		MemberDto dto = new MemberDto();
		dto.setUsername(data.getUsername());
		dto.setPassword(data.getPassword());
		return dto;
	}

}
