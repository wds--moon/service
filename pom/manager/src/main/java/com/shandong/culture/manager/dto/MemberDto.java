package com.shandong.culture.manager.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_EMPTY)
public class MemberDto implements Serializable {

	private static final long serialVersionUID = 4692878114828910772L;

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String email;

	private String mobile;

	/**
	 * 用户类型
	 */
	private String type;

}
