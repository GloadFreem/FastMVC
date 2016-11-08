package com.message.Enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * OriginalImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="original_img"
    ,catalog="jinzht2016"
)
@JsonIgnoreProperties({"original"})
public class OriginalImg  implements java.io.Serializable {


    // Fields    

     private Integer imgId;
     private Original original;
     private String url;


    // Constructors

    /** default constructor */
    public OriginalImg() {
    }

    
    /** full constructor */
    public OriginalImg(Original original, String url) {
        this.original = original;
        this.url = url;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="img_id", unique=true, nullable=false)

    public Integer getImgId() {
        return this.imgId;
    }
    
    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }
	@ManyToOne(fetch=FetchType.EAGER)
        @JoinColumn(name="info_id")

    public Original getOriginal() {
        return this.original;
    }
    
    public void setOriginal(Original original) {
        this.original = original;
    }
    
    @Column(name="url", length=65535)

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
   








}