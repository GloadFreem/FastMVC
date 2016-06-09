package com.jinzht.web.entity;
// default package


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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"publiccontents","collections"
		,"communions","inviterecords","attentions","systemmessages",
		"rewardsystems","actionprises","capitalaccounts","investmentrecords",
		"contentprises","projectcommitrecord","traderecords","systemcodes",
		"actionshare","actioncomments","loginfailrecords"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Project implements java.io.Serializable {

	// Fields

	private Integer projectId;
	private Financestatus financestatus;
	private String abbrevName;
	private String fullName;
	private String description;
	private Short projectType;
	private String address;
	private String startPageImage;
	private Integer collectionCount;
	private Integer timeLeft;
	private Set<Scene> scenes = new HashSet<Scene>(0);
	private Set<Communion> communions = new HashSet<Communion>(0);
	private Set<Projectcomment> projectcomments = new HashSet<Projectcomment>(0);
	private Controlreport controlreport;
	private Financingexit financingexit;
	private Financialstanding financialstanding;
	private Financingcase financingcase;
	private Investmentrecord investmentrecord;
	private Set<Projectcommitrecord> projectcommitrecords = new HashSet<Projectcommitrecord>(
			0);
	private Set<Team> teams = new HashSet<Team>(0);
	private Businessplan businessplan;
	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Member> members = new HashSet<Member>(0);
	private Set<Roadshow> roadshows = new HashSet<Roadshow>(0);
	private Set<Projectimages> projectimageses = new HashSet<Projectimages>(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(Financestatus financestatus, String abbrevName,
			String fullName, String description, Short projectType,
			String address, String startPageImage, Integer collectionCount,
			Integer timeLeft, Scene scene, Set<Communion> communions,
			Set<Projectcomment> projectcomments, Controlreport controlreport,
			Financingexit financingexit, Financialstanding financialstanding,
			Financingcase financingcase, Investmentrecord investmentrecord,
			Set<Projectcommitrecord> projectcommitrecords, Set<Team> teams,
			Businessplan businessplan, Set<Collection> collections,
			Set<Member> members, Set<Roadshow> roadshows,
			Set<Projectimages> projectimageses, Set<Scene> scenes) {
		this.financestatus = financestatus;
		this.abbrevName = abbrevName;
		this.fullName = fullName;
		this.description = description;
		this.projectType = projectType;
		this.address = address;
		this.startPageImage = startPageImage;
		this.collectionCount = collectionCount;
		this.timeLeft = timeLeft;
		this.scenes = scenes;
		this.communions = communions;
		this.projectcomments = projectcomments;
		this.controlreport = controlreport;
		this.financingexit = financingexit;
		this.financialstanding = financialstanding;
		this.financingcase = financingcase;
		this.investmentrecord = investmentrecord;
		this.projectcommitrecords = projectcommitrecords;
		this.teams = teams;
		this.businessplan = businessplan;
		this.collections = collections;
		this.members = members;
		this.roadshows = roadshows;
		this.projectimageses = projectimageses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "project_id", unique = true, nullable = false)
	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	public Financestatus getFinancestatus() {
		return this.financestatus;
	}

	public void setFinancestatus(Financestatus financestatus) {
		this.financestatus = financestatus;
	}

	@Column(name = "abbrev_name")
	public String getAbbrevName() {
		return this.abbrevName;
	}

	public void setAbbrevName(String abbrevName) {
		this.abbrevName = abbrevName;
	}

	@Column(name = "full_name")
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "project_type")
	public Short getProjectType() {
		return this.projectType;
	}

	public void setProjectType(Short projectType) {
		this.projectType = projectType;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "start_page_image", length = 200)
	public String getStartPageImage() {
		return this.startPageImage;
	}

	public void setStartPageImage(String startPageImage) {
		this.startPageImage = startPageImage;
	}

	@Column(name = "collectionCount")
	public Integer getCollectionCount() {
		return this.collectionCount;
	}

	public void setCollectionCount(Integer collectionCount) {
		this.collectionCount = collectionCount;
	}

	@Column(name = "timeLeft")
	public Integer getTimeLeft() {
		return this.timeLeft;
	}

	public void setTimeLeft(Integer timeLeft) {
		this.timeLeft = timeLeft;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Scene> getScenes() {
		return this.scenes;
	}

	public void setScenes(Set<Scene> scenes) {
		this.scenes = scenes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Communion> getCommunions() {
		return this.communions;
	}

	public void setCommunions(Set<Communion> communions) {
		this.communions = communions;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Projectcomment> getProjectcomments() {
		return this.projectcomments;
	}

	public void setProjectcomments(Set<Projectcomment> projectcomments) {
		this.projectcomments = projectcomments;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
	public Controlreport getControlreport() {
		return this.controlreport;
	}

	public void setControlreport(Controlreport controlreport) {
		this.controlreport = controlreport;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
	public Financingexit getFinancingexit() {
		return this.financingexit;
	}

	public void setFinancingexit(Financingexit financingexit) {
		this.financingexit = financingexit;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
	public Financialstanding getFinancialstanding() {
		return this.financialstanding;
	}

	public void setFinancialstanding(Financialstanding financialstanding) {
		this.financialstanding = financialstanding;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
	public Financingcase getFinancingcase() {
		return this.financingcase;
	}

	public void setFinancingcase(Financingcase financingcase) {
		this.financingcase = financingcase;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
	public Investmentrecord getInvestmentrecord() {
		return this.investmentrecord;
	}

	public void setInvestmentrecord(Investmentrecord investmentrecord) {
		this.investmentrecord = investmentrecord;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Projectcommitrecord> getProjectcommitrecords() {
		return this.projectcommitrecords;
	}

	public void setProjectcommitrecords(
			Set<Projectcommitrecord> projectcommitrecords) {
		this.projectcommitrecords = projectcommitrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
	public Businessplan getBusinessplan() {
		return this.businessplan;
	}

	public void setBusinessplan(Businessplan businessplan) {
		this.businessplan = businessplan;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Member> getMembers() {
		return this.members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Roadshow> getRoadshows() {
		return this.roadshows;
	}

	public void setRoadshows(Set<Roadshow> roadshows) {
		this.roadshows = roadshows;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Projectimages> getProjectimageses() {
		return this.projectimageses;
	}

	public void setProjectimageses(Set<Projectimages> projectimageses) {
		this.projectimageses = projectimageses;
	}

}