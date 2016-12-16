package com.jinzht.web.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProjectType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project_type", catalog = "jinzht2016")
public class ProjectType implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private Integer key;
	private String value;

	// Constructors

	/** default constructor */
	public ProjectType() {
	}

	/** full constructor */
	public ProjectType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "type_id", unique = true, nullable = false)
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "key")
	public Integer getKey() {
		return this.key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	@Column(name = "value")
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}