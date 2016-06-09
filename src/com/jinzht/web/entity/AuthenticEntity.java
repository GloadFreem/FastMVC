package com.jinzht.web.entity;

import java.io.File;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class AuthenticEntity {
//	@Min(value=1,message="{Pattern.authentic.areaid.notnull}")
	private String areaId = "" ;
	@Min(value=1,message="{Pattern.authentic.identiyTypeId.notnull}")
	private Short identiyTypeId = 0 ;
//	@Min(value=1,message="{Pattern.authentic.industoryId.notnull}")
	private Integer industoryId = 0;
	@Min(value=1,message="{Pattern.authentic.cityId.notnull}")
	private Integer cityId = 0;
	@NotNull(message="{Pattern.user.reaalname.notnull}")
	private String name;
	private MultipartFile identiyCarA;
	private MultipartFile identiyCarB;
	private String identiyCarNo;
	private String companyName;
	private String companyAddress;
	private String position;
	private MultipartFile buinessLicence;
	private String buinessLicenceNo;
	private String introduce;
	private String companyIntroduce;
	private String optional;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getIdentiyCarNo() {
		return identiyCarNo;
	}
	public void setIdentiyCarNo(String identiyCarNo) {
		this.identiyCarNo = identiyCarNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public MultipartFile getBuinessLicence() {
		return buinessLicence;
	}
	public void setBuinessLicence(MultipartFile buinessLicence) {
		this.buinessLicence = buinessLicence;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getBuinessLicenceNo() {
		return buinessLicenceNo;
	}
	public void setBuinessLicenceNo(String buinessLicenceNo) {
		this.buinessLicenceNo = buinessLicenceNo;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCompanyIntroduce() {
		return companyIntroduce;
	}
	public void setCompanyIntroduce(String companyIntroduce) {
		this.companyIntroduce = companyIntroduce;
	}
	public String getOptional() {
		return optional;
	}
	public void setOptional(String optional) {
		this.optional = optional;
	}
	public MultipartFile getIdentiyCarA() {
		return identiyCarA;
	}
	public void setIdentiyCarA(MultipartFile identiyCarA) {
		this.identiyCarA = identiyCarA;
	}
	public MultipartFile getIdentiyCarB() {
		return identiyCarB;
	}
	public void setIdentiyCarB(MultipartFile identiyCarB) {
		this.identiyCarB = identiyCarB;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Short getIdentiyTypeId() {
		return identiyTypeId;
	}
	public void setIdentiyTypeId(Short identiyTypeId) {
		this.identiyTypeId = identiyTypeId;
	}
	public Integer getIndustoryId() {
		return industoryId;
	}
	public void setIndustoryId(Integer industoryId) {
		this.industoryId = industoryId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
}
