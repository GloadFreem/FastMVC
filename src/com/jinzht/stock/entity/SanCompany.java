package com.jinzht.stock.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OrderBy;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * SanCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "san_company", catalog = "jinzht2016")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SanCompany implements java.io.Serializable {


	private Integer cid;        //id
	private String CName;    //简称
	private String CCode;    //代码
	private String CAddress;   //地址
	private String CType;   //行业标签
	private String CCreateDate;   //创建日期
	private String CCorporate;    //法人
	private String CSecretary;    //秘书
	private String CTel;    //电话
	private String sharesNum;     //总股本
	private String securitiesTrader;     //主办券商
	private String assignment;     //转让方式
	private String CDesc;     //公司简介
	private String sharesType;     //股票类型  0；基础层；1：创新层
	private String CTest;     //预留字段
	private String sanCompanycol;     //area
	private String CFullname;     //  全称
    //高管
	private Set<SanManagementer> sanManagementers = new HashSet<SanManagementer>(0);    
	//总负债
	private Set<SanLiabilities> sanLiabilitieses = new HashSet<SanLiabilities>(0);     
	//总资产
	private Set<SanAsset> sanAssets = new HashSet<SanAsset>(0);
	//公司股东
	private Set<SanShareholder> sanShareholders = new HashSet<SanShareholder>(0);
	//利润
	private Set<SanProfit> sanProfits = new HashSet<SanProfit>(0);
	//营业收入
	private Set<SanIncome> sanIncomes = new HashSet<SanIncome>(0);

	// Constructors

	/** default constructor */
	public SanCompany() {
	}

	/** full constructor */
	public SanCompany(String CName, String CCode, String CAddress,
			String CType, String CCreateDate, String CCorporate,
			String CSecretary, String CTel, String sharesNum,
			String securitiesTrader, String assignment, String CDesc,
			String sharesType, String CTest, String sanCompanycol,
			String CFullname, Set<SanManagementer> sanManagementers,
			Set<SanLiabilities> sanLiabilitieses, Set<SanAsset> sanAssets,
			Set<SanShareholder> sanShareholders, Set<SanProfit> sanProfits,
			Set<SanIncome> sanIncomes) {
		this.CName = CName;
		this.CCode = CCode;
		this.CAddress = CAddress;
		this.CType = CType;
		this.CCreateDate = CCreateDate;
		this.CCorporate = CCorporate;
		this.CSecretary = CSecretary;
		this.CTel = CTel;
		this.sharesNum = sharesNum;
		this.securitiesTrader = securitiesTrader;
		this.assignment = assignment;
		this.CDesc = CDesc;
		this.sharesType = sharesType;
		this.CTest = CTest;
		this.sanCompanycol = sanCompanycol;
		this.CFullname = CFullname;
		this.sanManagementers = sanManagementers;
		this.sanLiabilitieses = sanLiabilitieses;
		this.sanAssets = sanAssets;
		this.sanShareholders = sanShareholders;
		this.sanProfits = sanProfits;
		this.sanIncomes = sanIncomes;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "cid", unique = true, nullable = false)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "c_name", length = 16777215)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "c_code")
	public String getCCode() {
		return this.CCode;
	}

	public void setCCode(String CCode) {
		this.CCode = CCode;
	}

	@Column(name = "c_address")
	public String getCAddress() {
		return this.CAddress;
	}

	public void setCAddress(String CAddress) {
		this.CAddress = CAddress;
	}

	@Column(name = "c_type")
	public String getCType() {
		return this.CType;
	}

	public void setCType(String CType) {
		this.CType = CType;
	}

	@Column(name = "c_createDate")
	public String getCCreateDate() {
		return this.CCreateDate;
	}

	public void setCCreateDate(String CCreateDate) {
		this.CCreateDate = CCreateDate;
	}

	@Column(name = "c_corporate")
	public String getCCorporate() {
		return this.CCorporate;
	}

	public void setCCorporate(String CCorporate) {
		this.CCorporate = CCorporate;
	}

	@Column(name = "c_secretary")
	public String getCSecretary() {
		return this.CSecretary;
	}

	public void setCSecretary(String CSecretary) {
		this.CSecretary = CSecretary;
	}

	@Column(name = "c_tel")
	public String getCTel() {
		return this.CTel;
	}

	public void setCTel(String CTel) {
		this.CTel = CTel;
	}

	@Column(name = "shares_num")
	public String getSharesNum() {
		return this.sharesNum;
	}

	public void setSharesNum(String sharesNum) {
		this.sharesNum = sharesNum;
	}

	@Column(name = "securities_trader")
	public String getSecuritiesTrader() {
		return this.securitiesTrader;
	}

	public void setSecuritiesTrader(String securitiesTrader) {
		this.securitiesTrader = securitiesTrader;
	}

	@Column(name = "assignment")
	public String getAssignment() {
		return this.assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	@Column(name = "c_desc", length = 16777215)
	public String getCDesc() {
		return this.CDesc;
	}

	public void setCDesc(String CDesc) {
		this.CDesc = CDesc;
	}

	@Column(name = "shares_type")
	public String getSharesType() {
		return this.sharesType;
	}

	public void setSharesType(String sharesType) {
		this.sharesType = sharesType;
	}

	@Column(name = "c_test")
	public String getCTest() {
		return this.CTest;
	}

	public void setCTest(String CTest) {
		this.CTest = CTest;
	}

	@Column(name = "san_companycol")
	public String getSanCompanycol() {
		return this.sanCompanycol;
	}

	public void setSanCompanycol(String sanCompanycol) {
		this.sanCompanycol = sanCompanycol;
	}

	@Column(name = "c_fullname")
	public String getCFullname() {
		return this.CFullname;
	}

	public void setCFullname(String CFullname) {
		this.CFullname = CFullname;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sanCompany")
	public Set<SanManagementer> getSanManagementers() {
		return this.sanManagementers;
	}

	public void setSanManagementers(Set<SanManagementer> sanManagementers) {
		this.sanManagementers = sanManagementers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sanCompany")
	@OrderBy(value = "year desc")
	public Set<SanLiabilities> getSanLiabilitieses() {
		return this.sanLiabilitieses;
	}

	public void setSanLiabilitieses(Set<SanLiabilities> sanLiabilitieses) {
		this.sanLiabilitieses = sanLiabilitieses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sanCompany")
	@OrderBy(value = "year desc")
	public Set<SanAsset> getSanAssets() {
		return this.sanAssets;
	}

	public void setSanAssets(Set<SanAsset> sanAssets) {
		this.sanAssets = sanAssets;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sanCompany")
	@OrderBy(value = "stock desc")
	public Set<SanShareholder> getSanShareholders() {
		return this.sanShareholders;
	}

	public void setSanShareholders(Set<SanShareholder> sanShareholders) {
		this.sanShareholders = sanShareholders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sanCompany")
	@OrderBy(value = "year desc")
	public Set<SanProfit> getSanProfits() {
		return this.sanProfits;
	}

	public void setSanProfits(Set<SanProfit> sanProfits) {
		this.sanProfits = sanProfits;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "sanCompany")
	@OrderBy(value = "year desc")
	public Set<SanIncome> getSanIncomes() {
		return this.sanIncomes;
	}

	public void setSanIncomes(Set<SanIncome> sanIncomes) {
		this.sanIncomes = sanIncomes;
	}
}