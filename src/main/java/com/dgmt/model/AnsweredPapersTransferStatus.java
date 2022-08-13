package com.dgmt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "B_ANSWER_TRANSFER_STATUS")
public class AnsweredPapersTransferStatus implements Serializable {
	
	@SequenceGenerator(name = "answerTransferStatusSeq", sequenceName = "B_ANSWER_STATUS_SEQ")
	@Id
	@GeneratedValue(generator = "answerTransferStatusSeq")
	@Column(name = "TSTATUS_ID")
	private Long id;
	
	@Column(name = "MSG_ID")
	private String messageId;
	
	@Column(name = "PAPERS_IDS")
	private String answeredPaperIds;
	
	@Column(name = "TTS_ANSWERED_QUESTIONPAPER_CNT")
	private int transferedPaperCount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TTS_TRANSFER_TIME")
	private Date transferTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TTS_RECEIVED_TIME")
	private Date receiptTime;
	
	@Column(name = "TTS_STATUS", length = 1)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getAnsweredPaperIds() {
		return answeredPaperIds;
	}

	public void setAnsweredPaperIds(String answeredPaperIds) {
		this.answeredPaperIds = answeredPaperIds;
	}

	public int getTransferedPaperCount() {
		return transferedPaperCount;
	}

	public void setTransferedPaperCount(int transferedPaperCount) {
		this.transferedPaperCount = transferedPaperCount;
	}

	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	public Date getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
