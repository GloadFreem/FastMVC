package com.message.Enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * MsgDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "msg_detail", catalog = "jinzht2016")
@JsonIgnoreProperties({"msg"})
public class MsgDetail implements java.io.Serializable {

	// Fields

	private Integer detailId;
	private Msg msg;
	private String content;
	private String oringl;

	// Constructors

	/** default constructor */
	public MsgDetail() {
	}

	/** full constructor */
	public MsgDetail(Msg msg, String content, String oringl) {
		this.msg = msg;
		this.content = content;
		this.oringl = oringl;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "detail_id", unique = true, nullable = false)
	public Integer getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "info_id")
	public Msg getMsg() {
		return this.msg;
	}

	public void setMsg(Msg msg) {
		this.msg = msg;
	}

	@Column(name = "content", length = 16777215)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "oringl")
	public String getOringl() {
		return this.oringl;
	}

	public void setOringl(String oringl) {
		this.oringl = oringl;
	}

}