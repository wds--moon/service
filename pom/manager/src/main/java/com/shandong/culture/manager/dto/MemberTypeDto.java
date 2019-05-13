package com.shandong.culture.manager.dto;

import lombok.Data;

/**
 * 用户类型实体
 * 
 * @author LiDong
 *
 */
@Data
public class MemberTypeDto {
	/**
	 * 类型名称
	 */
	private String name;

	public MemberTypeDto(String name) {
		super();
		this.name = name;
	}

	public MemberTypeDto() {
		super();
	}

}
