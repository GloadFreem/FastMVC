package com.jinzht.web.entity;
// default package



import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jinzht.tools.Config;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"publiccontents","collections"
		,"communions","inviterecords","attentions","systemmessages",
		"rewardsystems","actionprises","capitalaccounts","investmentrecords",
		"contentprises","projectcommitrecord","traderecords","systemcodes",
		"actionshare","actioncomments","loginfailrecords","projectcomments",
		"projectcommitrecords","investorcollectsForUserCollectedId","investorcollectsForUserId"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Users implements java.io.Serializable {

	// Fields
	private String name;
	private Integer userId;
	private Userstatus userstatus;
	private String telephone;
	private String password;
	private String headSculpture;
	private Date lastLoginDate;
	private Short platform;
	private String wechatId;
	private Set<Publiccontent> publiccontents = new HashSet<Publiccontent>(0);
	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Communion> communions = new HashSet<Communion>(0);
	private Set<Inviterecord> inviterecords = new HashSet<Inviterecord>(0);
	private Set<Authentic> authentics = new HashSet<Authentic>(0);
	private Set<Attention> attentions = new HashSet<Attention>(0);
	private Set<Systemmessage> systemmessages = new HashSet<Systemmessage>(0);
	private Set<Rewardsystem> rewardsystems = new HashSet<Rewardsystem>(0);
	private Set<Actionprise> actionprises = new HashSet<Actionprise>(0);
	private Set<Capitalaccount> capitalaccounts = new HashSet<Capitalaccount>(0);
	private Set<Investmentrecord> investmentrecords = new HashSet<Investmentrecord>(
			0);
	private Set<Contentprise> contentprises = new HashSet<Contentprise>(0);
	private Set<Projectcommitrecord> projectcommitrecords = new HashSet<Projectcommitrecord>(
			0);
	private Set<Traderecord> traderecords = new HashSet<Traderecord>(0);
	private Set<Systemcode> systemcodes = new HashSet<Systemcode>(0);
	private Actionshare actionshare;
	private Set<Actioncomment> actioncomments = new HashSet<Actioncomment>(0);
	private Set<Loginfailrecord> loginfailrecords = new HashSet<Loginfailrecord>(
			0);
	private Set<Projectcomment> projectcomments = new HashSet<Projectcomment>(0);
	
	private Set<Investorcollect> investorcollectsForUserCollectedId = new HashSet<Investorcollect>(
			0);
	private Set<Investorcollect> investorcollectsForUserId = new HashSet<Investorcollect>(
			0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(Userstatus userstatus, String telephone, String password,
			String headSculpture, Date lastLoginDate, Short platform,
			String wechatId, Set<Publiccontent> publiccontents,
		    Set<Collection> collections,
		    Set<Projectcomment> projectcomments,
			Set<Communion> communions, Set<Inviterecord> inviterecords,
			Set<Authentic> authentics, Set<Attention> attentions,
			Set<Systemmessage> systemmessages, Set<Rewardsystem> rewardsystems,
			Set<Actionprise> actionprises, Set<Capitalaccount> capitalaccounts,
			Set<Investmentrecord> investmentrecords,
			Set<Contentprise> contentprises,
			Set<Projectcommitrecord> projectcommitrecords,
			Set<Traderecord> traderecords, Set<Systemcode> systemcodes,
			Actionshare actionshare,
			Set<Actioncomment> actioncomments,
			Set<Investorcollect> investorcollectsForUserCollectedId,
			Set<Investorcollect> investorcollectsForUserId,
			Set<Investorcollect> investorcollectsForUsersByUserCollectedId,
			Set<Loginfailrecord> loginfailrecords) {
		this.userstatus = userstatus;
		this.telephone = telephone;
		this.password = password;
		this.headSculpture = headSculpture;
		this.lastLoginDate = lastLoginDate;
		this.platform = platform;
		this.wechatId = wechatId;
		this.publiccontents = publiccontents;
		this.collections = collections;
		this.communions = communions;
		this.inviterecords = inviterecords;
		this.projectcomments = projectcomments;
		this.authentics = authentics;
		this.attentions = attentions;
		this.systemmessages = systemmessages;
		this.rewardsystems = rewardsystems;
		this.actionprises = actionprises;
		this.capitalaccounts = capitalaccounts;
		this.investmentrecords = investmentrecords;
		this.contentprises = contentprises;
		this.projectcommitrecords = projectcommitrecords;
		this.traderecords = traderecords;
		this.systemcodes = systemcodes;
		this.actionshare = actionshare;
		this.actioncomments = actioncomments;
		this.loginfailrecords = loginfailrecords;
		this.investorcollectsForUserId = investorcollectsForUserId;
		this.investorcollectsForUserCollectedId = investorcollectsForUserCollectedId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_status_id")
	public Userstatus getUserstatus() {
		return this.userstatus;
	}

	public void setUserstatus(Userstatus userstatus) {
		this.userstatus = userstatus;
	}

	@Column(name = "telephone", length = 11)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "head_sculpture")
	public String getHeadSculpture() {
		return this.headSculpture;
	}

	public void setHeadSculpture(String headSculpture) {
		this.headSculpture = headSculpture;
	}

	@Column(name = "last_login_date", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Column(name = "platform")
	public Short getPlatform() {
		return this.platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
	}

	@Column(name = "wechatID", length = 45)
	public String getWechatId() {
		return this.wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	public Set<Publiccontent> getPubliccontents() {
		return this.publiccontents;
	}

	public void setPubliccontents(Set<Publiccontent> publiccontents) {
		this.publiccontents = publiccontents;
	}


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Communion> getCommunions() {
		return this.communions;
	}

	public void setCommunions(Set<Communion> communions) {
		this.communions = communions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Inviterecord> getInviterecords() {
		return this.inviterecords;
	}

	public void setInviterecords(Set<Inviterecord> inviterecords) {
		this.inviterecords = inviterecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	public Set<Authentic> getAuthentics() {
		return this.authentics;
	}

	public void setAuthentics(Set<Authentic> authentics) {
		this.authentics = authentics;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Attention> getAttentions() {
		return this.attentions;
	}

	public void setAttentions(Set<Attention> attentions) {
		this.attentions = attentions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Systemmessage> getSystemmessages() {
		return this.systemmessages;
	}

	public void setSystemmessages(Set<Systemmessage> systemmessages) {
		this.systemmessages = systemmessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Rewardsystem> getRewardsystems() {
		return this.rewardsystems;
	}

	public void setRewardsystems(Set<Rewardsystem> rewardsystems) {
		this.rewardsystems = rewardsystems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Actionprise> getActionprises() {
		return this.actionprises;
	}

	public void setActionprises(Set<Actionprise> actionprises) {
		this.actionprises = actionprises;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Capitalaccount> getCapitalaccounts() {
		return this.capitalaccounts;
	}

	public void setCapitalaccounts(Set<Capitalaccount> capitalaccounts) {
		this.capitalaccounts = capitalaccounts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Investmentrecord> getInvestmentrecords() {
		return this.investmentrecords;
	}

	public void setInvestmentrecords(Set<Investmentrecord> investmentrecords) {
		this.investmentrecords = investmentrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Contentprise> getContentprises() {
		return this.contentprises;
	}

	public void setContentprises(Set<Contentprise> contentprises) {
		this.contentprises = contentprises;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Projectcommitrecord> getProjectcommitrecords() {
		return this.projectcommitrecords;
	}

	public void setProjectcommitrecords(
			Set<Projectcommitrecord> projectcommitrecords) {
		this.projectcommitrecords = projectcommitrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Traderecord> getTraderecords() {
		return this.traderecords;
	}

	public void setTraderecords(Set<Traderecord> traderecords) {
		this.traderecords = traderecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
	public Set<Systemcode> getSystemcodes() {
		return this.systemcodes;
	}

	public void setSystemcodes(Set<Systemcode> systemcodes) {
		this.systemcodes = systemcodes;
	}


	@OneToOne(fetch = FetchType.LAZY, mappedBy = "users")
	public Actionshare getActionshare() {
		return this.actionshare;
	}

	public void setActionshare(Actionshare actionshare) {
		this.actionshare = actionshare;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByUserId")
	public Set<Actioncomment> getActioncomments() {
		return this.actioncomments;
	}

	public void setActioncomments(Set<Actioncomment> actioncomments) {
		this.actioncomments = actioncomments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Loginfailrecord> getLoginfailrecords() {
		return this.loginfailrecords;
	}

	public void setLoginfailrecords(Set<Loginfailrecord> loginfailrecords) {
		this.loginfailrecords = loginfailrecords;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Projectcomment> getProjectcomments() {
		return this.projectcomments;
	}

	public void setProjectcomments(Set<Projectcomment> projectcomments) {
		this.projectcomments = projectcomments;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByUserCollectedId")
	public Set<Investorcollect> getInvestorcollectsForUserCollectedId() {
		return this.investorcollectsForUserCollectedId;
	}

	public void setInvestorcollectsForUserCollectedId(
			Set<Investorcollect> investorcollectsForUserCollectedId) {
		this.investorcollectsForUserCollectedId = investorcollectsForUserCollectedId;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersByUserId")
	public Set<Investorcollect> getInvestorcollectsForUserId() {
		return this.investorcollectsForUserId;
	}

	public void setInvestorcollectsForUserId(
			Set<Investorcollect> investorcollectsForUserId) {
		this.investorcollectsForUserId = investorcollectsForUserId;
	}

	

}