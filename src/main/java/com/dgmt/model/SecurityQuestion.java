package com.dgmt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "L_SECURITY_QUESTION")
public class SecurityQuestion implements Serializable
{
	@Id
	@Column(name = "SQ_ID")
	private Long id;

	@Column(name = "SQ_DESC", unique = true, nullable = false)
	private String desc;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	
	

}
