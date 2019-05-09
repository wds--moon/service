package com.shandong.culture.manager.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberDto implements Serializable {

	private static final long serialVersionUID = 4692878114828910772L;

	private Long id;

	private String username;

	private String password;

	private String email;

	private String mobile;

}
