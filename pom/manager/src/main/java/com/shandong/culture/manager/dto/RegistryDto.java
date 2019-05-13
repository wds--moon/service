package com.shandong.culture.manager.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 用户注册实体
 * 
 * @author LiDong
 *
 */
@Data
public class RegistryDto implements Serializable {
	private static final long serialVersionUID = 1807319011044452015L;

	@NotBlank
	private String username;

	@NotBlank
	@Length(min = 6)
	private String password;

	@NotBlank
	private String repassword;
}
