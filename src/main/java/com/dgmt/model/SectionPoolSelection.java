package com.dgmt.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "B_SECTION_POOL")
public class SectionPoolSelection implements Serializable
{
	@Id
	@Column(name = "SP_ID")
	private Long id;

	@Column(name = "SP_QUESTION_COUNT")
	private int queCount;

	@ManyToOne(cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@JoinColumn(name="QP_ID")
	private OleQuestionPool pool;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public int getQueCount()
	{
		return queCount;
	}

	public void setQueCount(int queCount)
	{
		this.queCount = queCount;
	}

	public OleQuestionPool getPool()
	{
		return pool;
	}

	public void setPool(OleQuestionPool pool)
	{
		this.pool = pool;
	}

}
