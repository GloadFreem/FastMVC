package com.jinzht.web.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * BusinessOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "business_order", catalog = "jinzht2016")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BusinessOrder implements java.io.Serializable {

	// Fields

	private Integer orderId;
	private Users users;
	private BusinessPayStatus businessPayStatus;
	private BusinessPayType businessPayType;
	private BusinessSchool businessSchool;
	private String orderCode;
	private Double totalFee;
	private Date orderTime;

	// Constructors

	/** default constructor */
	public BusinessOrder() {
	}

	/** minimal constructor */
	public BusinessOrder(Integer orderId) {
		this.orderId = orderId;
	}

	/** full constructor */
	public BusinessOrder(Integer orderId, Users users,
			BusinessPayStatus businessPayStatus,
			BusinessPayType businessPayType, BusinessSchool businessSchool,
			String orderCode, Double totalFee, Date orderTime) {
		this.orderId = orderId;
		this.users = users;
		this.businessPayStatus = businessPayStatus;
		this.businessPayType = businessPayType;
		this.businessSchool = businessSchool;
		this.orderCode = orderCode;
		this.totalFee = totalFee;
		this.orderTime = orderTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_status_id")
	public BusinessPayStatus getBusinessPayStatus() {
		return this.businessPayStatus;
	}

	public void setBusinessPayStatus(BusinessPayStatus businessPayStatus) {
		this.businessPayStatus = businessPayStatus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pay_type_id")
	public BusinessPayType getBusinessPayType() {
		return this.businessPayType;
	}

	public void setBusinessPayType(BusinessPayType businessPayType) {
		this.businessPayType = businessPayType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_school_id")
	public BusinessSchool getBusinessSchool() {
		return this.businessSchool;
	}

	public void setBusinessSchool(BusinessSchool businessSchool) {
		this.businessSchool = businessSchool;
	}

	@Column(name = "order_code")
	public String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Column(name = "total_fee", precision = 22, scale = 0)
	public Double getTotalFee() {
		return this.totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	@Column(name = "order_time", length = 19)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

}