/**
 * Created Date: 4/12/10 11:29 AM
 * Class Name  : TestCenter.java
 *
 * © Copyright 2010 Cambridge Technology Enterprises Ltd.,All rights reserved.
 *
 * * * * * * * * * * * * * * * Change History *  * * * * * * * * * * *
 * <Defect Tag>        <Author>        <Date>        <Comments on Change>
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package com.dgmt.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Check;

/**
 * Description:
 * 
 * @version 1.0 4/12/10 11:29 AM
 * @author CTE
 * @since DGMT 1.0
 */
@Entity
@Table(name = "B_TEST_CENTER")
public class TestCenter extends BaseTestCenter implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2890043905092789321L;

	/**  */
	@Id
	@Column(name = "TC_ID")
	private Long id;

	/**  */
	@Column(name = "TC_NAME", nullable = false, unique = true, length = 30)
	private String name;

	/**  */
	@Column(name = "TC_ADDR1", length = 50)
	private String addr1;

	/**  */
	@Column(name = "TC_ADDR2", length = 50)
	private String addr2;

	/**  */
	@Column(name = "TC_CITY", nullable = false, length = 30)
	private String city;

	/**  */
	@ManyToOne
	@Cascade( { CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "STATE_ID")
	private State state;

	/**  */
	@Column(name = "TC_PINCODE", length = 10)
	private String pincode;

	/**  */
	@Column(name = "TC_PHONE_CODE", length = 10)
	private String phoneCode;

	/**  */
	@Column(name = "TC_PHONE_NUMBER", length = 10)
	private String phoneNumber;

	/**  */
	@Column(name = "TC_CONTACT", length = 30)
	private String contactPerson;

	/**  */
	@Column(name = "TC_TERMINAL_COUNT")
	private int terminalCount;

	/**  */
	@Column(name = "TC_RES_TERMINAL_COUNT")
	private int resTerminalCount;

	@Column(name = "TC_CODE", length = 2, unique = true, nullable = false)
	private String code;

	/**  */
	@Column(name = "TC_STATUS", length = 2)
	@Check(constraints = "tc_status = 0 or tc_status = 1")
	private String status;

	@Column(name = "TC_MODIFIED_BY", length = 20)
	private String modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TC_MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "TC_CREATED_BY", length = 20)
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TC_CREATED_DATE")
	private Date createdDate;

	@Column(name = "TC_FROM_IPADDRESS", length = 20)
	private String fromIpAddress;

	@Column(name = "TC_TO_IPADDRESS", length = 20)
	private String toIpAddress;

	@Column(name = "TC_SERVER_IP_ADDRESS", length = 20)
	private String serverIpAddress;

	@Column(name = "TC_SERVER_PORT_NO", length = 5)
	private Long serverPortNo;

	/**
	 * @return
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return
	 */
	public State getState()
	{
		return state;
	}

	/**
	 * @param state
	 */
	public void setState(State state)
	{
		this.state = state;
	}

	/**
	 * @return
	 */
	public String getPincode()
	{
		return pincode;
	}

	/**
	 * @param pincode
	 */
	public void setPincode(String pincode)
	{
		this.pincode = pincode;
	}

	/**
	 * @return
	 */
	public String getPhoneCode()
	{
		return phoneCode;
	}

	/**
	 * @param phoneCode
	 */
	public void setPhoneCode(String phoneCode)
	{
		this.phoneCode = phoneCode;
	}

	/**
	 * @return
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return
	 */
	public String getContactPerson()
	{
		return contactPerson;
	}

	/**
	 * @param contactPerson
	 */
	public void setContactPerson(String contactPerson)
	{
		this.contactPerson = contactPerson;
	}

	/**
	 * @return
	 */
	public String getAddr1()
	{
		return addr1;
	}

	/**
	 * @param addr1
	 */
	public void setAddr1(String addr1)
	{
		this.addr1 = addr1;
	}

	/**
	 * @return
	 */
	public String getAddr2()
	{
		return addr2;
	}

	/**
	 * @param addr2
	 */
	public void setAddr2(String addr2)
	{
		this.addr2 = addr2;
	}

	/**
	 * @return
	 */
	public int getTerminalCount()
	{
		return terminalCount;
	}

	/**
	 * @param terminalCount
	 */
	public void setTerminalCount(int terminalCount)
	{
		this.terminalCount = terminalCount;
	}

	/**
	 * @return
	 */
	public int getResTerminalCount()
	{
		return resTerminalCount;
	}

	/**
	 * @param resTerminalCount
	 */
	public void setResTerminalCount(int resTerminalCount)
	{
		this.resTerminalCount = resTerminalCount;
	}

	/**
	 * @return
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @param status
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @return
	 */
	public String getModifiedBy()
	{
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 */
	public void setModifiedBy(String modifiedBy)
	{
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return
	 */
	public Date getModifiedDate()
	{
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 */
	public void setModifiedDate(Date modifiedDate)
	{
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * @return
	 */
	public Date getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * @param createdDate
	 */
	public void setCreatedDate(Date createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof TestCenter))
		{
			return false;
		}

		return this.id.intValue() == ((TestCenter) obj).getId().intValue();
	}

	/**
	 * @return
	 */
	@Override
	public int hashCode()
	{
		return id.intValue();
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @return
	 */
	public String getFromIpAddress()
	{
		return fromIpAddress;
	}

	/**
	 * @param fromIpAddress
	 */
	public void setFromIpAddress(String fromIpAddress)
	{
		this.fromIpAddress = fromIpAddress;
	}

	/**
	 * @return
	 */
	public String getToIpAddress()
	{
		return toIpAddress;
	}

	/**
	 * @param toIpAddress
	 */
	public void setToIpAddress(String toIpAddress)
	{
		this.toIpAddress = toIpAddress;
	}

	/**
	 * @return
	 */
	public String getServerIpAddress()
	{
		return serverIpAddress;
	}

	/**
	 * @param serverIpAddress
	 */
	public void setServerIpAddress(String serverIpAddress)
	{
		this.serverIpAddress = serverIpAddress;
	}

	/**
	 * @return
	 */
	public Long getServerPortNo()
	{
		return serverPortNo;
	}

	/**
	 * @param serverPortNo
	 */
	public void setServerPortNo(Long serverPortNo)
	{
		this.serverPortNo = serverPortNo;
	}
}
