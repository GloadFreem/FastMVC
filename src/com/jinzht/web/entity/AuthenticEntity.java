package com.jinzht.web.entity;

import java.io.File;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthenticEntity {
	@Min(value=1,message="{Pattern.authentic.areaid.notnull}")
	private Integer areaId = 0 ;
	@Min(value=1,message="{Pattern.authentic.identiyTypeId.notnull}")
	private Short identiyTypeId = 0 ;
	@Min(value=1,message="{Pattern.authentic.industoryId.notnull}")
	private Integer industoryId = 0;
	private City city;
	private String name;
	private File identiyCarA;
	private File identiyCarB;
	private String identiyCarNo;
	private String companyName;
	private String companyAddress;
	private String position;
	private String buinessLicence;
	private String buinessLicenceNo;
	private String introduce;
	private String companyIntroduce;
	private Short optional;
	
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
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
	public String getBuinessLicence() {
		return buinessLicence;
	}
	public void setBuinessLicence(String buinessLicence) {
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
	public Short getOptional() {
		return optional;
	}
	public void setOptional(Short optional) {
		this.optional = optional;
	}
	public File getIdentiyCarA() {
		return identiyCarA;
	}
	public void setIdentiyCarA(File identiyCarA) {
		this.identiyCarA = identiyCarA;
	}
	public File getIdentiyCarB() {
		return identiyCarB;
	}
	public void setIdentiyCarB(File identiyCarB) {
		this.identiyCarB = identiyCarB;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
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
	
}
