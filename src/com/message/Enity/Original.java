package com.message.Enity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jinzht.web.entity.Webcontenttype;

/**
 * Original entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "original", catalog = "jinzht2016")
@JsonIgnoreProperties({"originalDetails"})
public class Original implements java.io.Serializable {

	// Fields

	private Integer infoId;
	private Webcontenttype webcontenttype;
	private String title;
	private String oringl;
	private String publicDate;
	private Integer tag;
	private Set<OriginalDetail> originalDetails = new HashSet<OriginalDetail>(0);
	private Set<OriginalImg> originalImgs = new HashSet<OriginalImg>(0);

	// Constructors

	/** default constructor */
	public Original() {
	}

	/** full constructor */
	public Original(Webcontenttype webcontenttype, String title, String oringl,
			String publicDate, Integer tag,
			Set<OriginalDetail> originalDetails, Set<OriginalImg> originalImgs) {
		this.webcontenttype = webcontenttype;
		this.title = title;
		this.oringl = oringl;
		this.publicDate = publicDate;
		this.tag = tag;
		this.originalDetails = originalDetails;
		this.originalImgs = originalImgs;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "info_id", unique = true, nullable = false)
	public Integer getInfoId() {
		return this.infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	public Webcontenttype getWebcontenttype() {
		return this.webcontenttype;
	}

	public void setWebcontenttype(Webcontenttype webcontenttype) {
		this.webcontenttype = webcontenttype;
	}

	@Column(name = "title", length = 65535)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "oringl", length = 65535)
	public String getOringl() {
		return this.oringl;
	}

	public void setOringl(String oringl) {
		this.oringl = oringl;
	}

	@Column(name = "public_date", length = 65535)
	public String getPublicDate() {
		return this.publicDate;
	}

	public void setPublicDate(String publicDate) {
		this.publicDate = publicDate;
	}

	@Column(name = "tag")
	public Integer getTag() {
		return this.tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "original")
	public Set<OriginalDetail> getOriginalDetails() {
		return this.originalDetails;
	}

	public void setOriginalDetails(Set<OriginalDetail> originalDetails) {
		this.originalDetails = originalDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "original")
	public Set<OriginalImg> getOriginalImgs() {
		return this.originalImgs;
	}

	public void setOriginalImgs(Set<OriginalImg> originalImgs) {
		this.originalImgs = originalImgs;
	}

}