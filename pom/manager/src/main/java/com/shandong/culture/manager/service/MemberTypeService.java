package com.shandong.culture.manager.service;

import java.util.List;

import com.shandong.culture.manager.dto.MemberTypeDto;
import com.shandong.culture.manager.entity.MemberType;

public interface MemberTypeService {

	public boolean exist();

	MemberType save(MemberTypeDto data);

	List<MemberType> save(List<MemberTypeDto> datas);

}
