package com.shandong.culture.manager.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dm.uap.entity.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员
 * 
 * @author LiDong
 *
 */
@Entity(name = "t_member_")
@Data
@EqualsAndHashCode(callSuper = true)
public class Member extends User {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "type_")
	private MemberType type;
}
