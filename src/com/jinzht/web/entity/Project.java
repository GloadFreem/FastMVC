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
import com.jinzht.tools.DateUtils;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project", catalog = "jinzht2016")
@JsonIgnoreProperties(value={"publiccontents","collections"
		,"communions","inviterecords","attentions","systemmessages",
		"rewardsystems","actionprises","capitalaccounts","investmentrecords",
		"contentprises","projectcommitrecord","traderecords","systemcodes",
		"actioncomments","loginfailrecords","scenes","projectcomments"
		,"projectcommitrecords","members","projectcommitrecords"})
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Project implements java.io.Serializable {

	// Fields

	private Integer projectId;
	private Integer userId;
	private String borrowerUserNumber;
	private Financestatus financestatus;
	private String abbrevName;
	private String fullName;
	private String description;
	private Short projectType;
	private String address;
	private Boolean collected; //是否关注
	private String startPageImage;
	private Integer collectionCount;
	private Integer commentCount;
	private Integer timeLeft;
	private String industoryType;
	private Set<Scene> scenes = new HashSet<Scene>(0);
	private Set<Communion> communions = new HashSet<Communion>(0);
	private Set<Projectcomment> projectcomments = new HashSet<Projectcomment>(0);
	private Set<Investmentrecord> investmentrecords = new HashSet<Investmentrecord>(
			0);
	private Set<Projectcommitrecord> projectcommitrecords = new HashSet<Projectcommitrecord>(
			0);
	private Set<Team> teams = new HashSet<Team>(0);
	private Set<Collection> collections = new HashSet<Collection>(0);
	private Set<Member> members = new HashSet<Member>(0);
	private Set<Roadshow> roadshows = new HashSet<Roadshow>(0);
	private Set<Projectimages> projectimageses = new HashSet<Projectimages>(0);

	private Set<Businessplan> businessplans = new HashSet<Businessplan>(0);
	private Set<Financingexit> financingexits = new HashSet<Financingexit>(0);
	private Set<Controlreport> controlreports = new HashSet<Controlreport>(0);
	private Set<Financingcase> financingcases = new HashSet<Financingcase>(0);
	private Set<Financialstanding> financialstandings = new HashSet<Financialstanding>();
	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(Financestatus financestatus, String abbrevName,
			String fullName, String description, Short projectType,
			String address, String startPageImage, Integer collectionCount,
			Integer timeLeft, Integer userId, String industoryType,
			Boolean collected, Integer commentCount, String borrowerUserNumber,
			Set<Scene> scenes, Set<Communion> communions,
			Set<Projectcomment> projectcomments,
			Set<Businessplan> businessplans, Set<Financingexit> financingexits,
			Set<Controlreport> controlreports,
			Set<Investmentrecord> investmentrecords,
			Set<Projectcommitrecord> projectcommitrecords,
			Set<Financingcase> financingcases, Set<Team> teams,
			Set<Collection> collections,
			Set<Financialstanding> financialstandings, Set<Member> members,
			Set<Roadshow> roadshows, Set<Projectimages> projectimageses) {
		this.financestatus = financestatus;
		this.abbrevName = abbrevName;
		this.fullName = fullName;
		this.description = description;
		this.projectType = projectType;
		this.address = address;
		this.startPageImage = startPageImage;
		this.collectionCount = collectionCount;
		this.timeLeft = timeLeft;
		this.userId = userId;
		this.industoryType = industoryType;
		this.collected = collected;
		this.commentCount = commentCount;
		this.borrowerUserNumber = borrowerUserNumber;
		this.scenes = scenes;
		this.communions = communions;
		this.projectcomments = projectcomments;
		this.businessplans = businessplans;
		this.financingexits = financingexits;
		this.controlreports = controlreports;
		this.investmentrecords = investmentrecords;
		this.projectcommitrecords = projectcommitrecords;
		this.financingcases = financingcases;
		this.teams = teams;
		this.collections = collections;
		this.financialstandings = financialstandings;
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

	@ManyToOne(fetch = FetchType.EAGER)
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
		Object[] objs = this.getRoadshows().toArray();
		if(objs!=null && objs.length>0)
		{
			for(Object obj :objs)
			{
				Roadshow roadShow = (Roadshow)obj;
				Roadshowplan plan = roadShow.getRoadshowplan();
				int days;
				try {
					if(plan!=null && plan.getBeginDate()!=null && plan.getEndDate()!=null)
					{
						days = DateUtils.getDaysBetween( plan.getBeginDate(),plan.getEndDate());
						this.timeLeft=days;
					}else{
						this.timeLeft = 0;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					this.timeLeft=0;
					e.printStackTrace();
				}
				
			}
		}
		
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


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Investmentrecord> getInvestmentrecords() {
		return this.investmentrecords;
	}

	public void setInvestmentrecords(Set<Investmentrecord> investmentrecords) {
		this.investmentrecords = investmentrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Projectcommitrecord> getProjectcommitrecords() {
		return this.projectcommitrecords;
	}

	public void setProjectcommitrecords(
			Set<Projectcommitrecord> projectcommitrecords) {
		this.projectcommitrecords = projectcommitrecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Team> getTeams() {
		return this.teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "project")
	public Set<Collection> getCollections() {
		return this.collections;
	}

	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Projectimages> getProjectimageses() {
		return this.projectimageses;
	}

	public void setProjectimageses(Set<Projectimages> projectimageses) {
		this.projectimageses = projectimageses;
	}
	
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getIndustoryType() {
		return industoryType;
	}
	@Column(name="industoryType")
	public void setIndustoryType(String industoryType) {
		this.industoryType = industoryType;
	}

	public Boolean getCollected() {
		return collected;
	}

	public void setCollected(Boolean collected) {
		this.collected = collected;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	@Column(name="borrower_user_number")
	public String getBorrowerUserNumber() {
		return borrowerUserNumber;
	}

	public void setBorrowerUserNumber(String borrowerUserNumber) {
		this.borrowerUserNumber = borrowerUserNumber;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Businessplan> getBusinessplans() {
		return this.businessplans;
	}

	public void setBusinessplans(Set<Businessplan> businessplans) {
		this.businessplans = businessplans;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Financingexit> getFinancingexits() {
		return this.financingexits;
	}

	public void setFinancingexits(Set<Financingexit> financingexits) {
		this.financingexits = financingexits;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Controlreport> getControlreports() {
		return this.controlreports;
	}

	public void setControlreports(Set<Controlreport> controlreports) {
		this.controlreports = controlreports;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Financingcase> getFinancingcases() {
		return this.financingcases;
	}

	public void setFinancingcases(Set<Financingcase> financingcases) {
		this.financingcases = financingcases;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "project")
	public Set<Financialstanding> getFinancialstandings() {
		return this.financialstandings;
	}

	public void setFinancialstandings(Set<Financialstanding> financialstandings) {
		this.financialstandings = financialstandings;
	}

}