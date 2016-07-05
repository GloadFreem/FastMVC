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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Contenttype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contenttype", catalog = "jinzht2016")
public class Contenttype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;
	private Set<Weburlrecord> weburlrecords = new HashSet<Weburlrecord>(0);

	// Constructors

	/** default constructor */
	public Contenttype() {
	}

	/** full constructor */
	public Contenttype(String name, Set<Weburlrecord> weburlrecords) {
		this.name = name;
		this.weburlrecords = weburlrecords;
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

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contenttype")
	public Set<Weburlrecord> getWeburlrecords() {
		return this.weburlrecords;
	}

	public void setWeburlrecords(Set<Weburlrecord> weburlrecords) {
		this.weburlrecords = weburlrecords;
	}

}