package com.shandong.culture.manager.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.shandong.culture.manager.dto.MemberTypeDto;
import com.shandong.culture.manager.service.MemberTypeService;

@Configuration
public class InitDataConfigure {

	@Autowired
	private MemberTypeService memberTypeService;

	@PostConstruct
	public void initData() {
		initMemeberTypes();
	}

	/**
	 * 初始化用户类型
	 */
	private void initMemeberTypes() {
		if (!memberTypeService.exist()) {
			List<MemberTypeDto> datas = new ArrayList<>();
			datas.add(new MemberTypeDto("学生"));
			datas.add(new MemberTypeDto("企业"));
			datas.add(new MemberTypeDto("个人"));
			datas.add(new MemberTypeDto("研究学者"));
			memberTypeService.save(datas);
		}
	}

}
