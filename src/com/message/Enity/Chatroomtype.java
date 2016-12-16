package com.message.Enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Chatroomtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "chatroomtype", catalog = "jinzht2016")
public class Chatroomtype implements java.io.Serializable {

	// Fields

	private Integer typeId;
	private String name;

	// Constructors

	/** default constructor */
	public Chatroomtype() {
	}

	/** full constructor */
	public Chatroomtype(String name) {
		this.name = name;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}