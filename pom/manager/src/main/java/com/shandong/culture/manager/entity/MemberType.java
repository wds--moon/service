package com.shandong.culture.manager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_member_type_")
public class MemberType implements Serializable {

	private static final long serialVersionUID = -5827519776149460989L;

	@Id
	@Column(name = "name_", length = 50)
	private String name;

	public MemberType(String name) {
		super();
		this.name = name;
	}

	public MemberType() {
		super();
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
