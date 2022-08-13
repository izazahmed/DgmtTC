/**
 * Created Date: 4/15/10 7:27 PM
 * Class Name  : Choice.java
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
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate.type.EncryptedStringType;
import org.hibernate.annotations.Parameter;

/**
 * Description:
 * 
 * @version 1.0 4/15/10 7:27 PM
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
@Table(name = "B_QUESTION_OPTIONS")
public class OleChoice implements Serializable
{
	/**
	 * DOCUMENT ME!
	 */
	@Id
	@Column(name = "QO_ID")
	int choiceId;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QO_OPTION", length = 4000, nullable = false)
	@Type(type="encryptedString")
	String optionText;

	/**
	 * DOCUMENT ME!
	 */
	@Column(name = "QO_IS_CORRECT_ANS", length = 1, nullable = false)
	int isAns;

	/**
	 * @return
	 */
	public int getChoiceId()
	{
		return choiceId;
	}

	/**
	 * @param choiceId
	 */
	public void setChoiceId(int choiceId)
	{
		this.choiceId = choiceId;
	}

	/**
	 * @return
	 */
	public String getOptionText()
	{
		return optionText;
	}

	/**
	 * @param optionText
	 */
	public void setOptionText(String optionText)
	{
		this.optionText = optionText;
	}

	/**
	 * @return
	 */
	public int getIsAns()
	{
		return isAns;
	}

	/**
	 * @param isAns
	 */
	public void setIsAns(int isAns)
	{
		this.isAns = isAns;
	}
}
