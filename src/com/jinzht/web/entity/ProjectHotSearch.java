package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProjectHotSearch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project_hot_search", catalog = "jinzht2016")
public class ProjectHotSearch implements java.io.Serializable {

	// Fields

	private Integer hotId;
	private String hotWord;
	private Integer hotNum;

	// Constructors

	/** default constructor */
	public ProjectHotSearch() {
	}

	/** full constructor */
	public ProjectHotSearch(String hotWord, Integer hotNum) {
		this.hotWord = hotWord;
		this.hotNum = hotNum;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "hot_id", unique = true, nullable = false)
	public Integer getHotId() {
		return this.hotId;
	}

	public void setHotId(Integer hotId) {
		this.hotId = hotId;
	}

	@Column(name = "hot_word")
	public String getHotWord() {
		return this.hotWord;
	}

	public void setHotWord(String hotWord) {
		this.hotWord = hotWord;
	}

	@Column(name = "hot_num")
	public Integer getHotNum() {
		return this.hotNum;
	}

	public void setHotNum(Integer hotNum) {
		this.hotNum = hotNum;
	}

}