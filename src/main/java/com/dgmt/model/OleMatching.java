/**
 * Created Date: 4/15/10 7:28 PM
 * Class Name  : Matching.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises(India) Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>	<Author>	<Date>	<Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package com.dgmt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate.type.EncryptedStringType;
import org.hibernate.annotations.Parameter;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:28 PM
 * @author
 * @since DGMT 1.0
 */
@TypeDef(
        name="encryptedString", 
        typeClass=EncryptedStringType.class, 
        parameters={@Parameter(name="encryptorRegisteredName",
                               value="strongHibernateStringEncryptor")}
    )

@Entity
@Table(name = "B_MATCH_THE_FOLLOWING")
public class OleMatching implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "QM_ID")
	int matchingId;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QM_COL1", length = 4000, nullable = false)
	@Type(type="encryptedString")
	String column1;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QM_COL2", length = 4000, nullable = false)
	@Type(type="encryptedString")
	String column2;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QM_CORRECT_ANS", length = 4000, nullable = false)
	@Type(type="encryptedString")
	String correctAns;

	/**
	 * @return
	 */
	public int getMatchingId()
	{
		return matchingId;
	}

	/**
	 * @param matchingId
	 */
	public void setMatchingId(int matchingId)
	{
		this.matchingId = matchingId;
	}

	/**
	 * @return
	 */
	public String getColumn1()
	{
		return column1;
	}

	/**
	 * @param column1
	 */
	public void setColumn1(String column1)
	{
		this.column1 = column1;
	}

	/**
	 * @return
	 */
	public String getColumn2()
	{
		return column2;
	}

	/**
	 * @param column2
	 */
	public void setColumn2(String column2)
	{
		this.column2 = column2;
	}

	/**
	 * @return
	 */
	public String getCorrectAns()
	{
		return correctAns;
	}

	/**
	 * @param correctAns
	 */
	public void setCorrectAns(String correctAns)
	{
		this.correctAns = correctAns;
	}
}
