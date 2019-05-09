package com.shandong.culture.manager.entity;

import javax.persistence.Entity;

import com.dm.uap.entity.User;

/**
 * 会员
 * 
 * @author LiDong
 *
 */
@Entity(name = "t_member_")
public class Member extends User {
	private static final long serialVersionUID = 1L;
}
