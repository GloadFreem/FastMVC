package com.message.Enity;

import com.mysql.fabric.xmlrpc.base.Array;

public class MsgBean {
	
	private String id;
	private String title;
	private String oringl;
	private String publicDate;
	private Webcontent  webcontentType;
	private String[] images;
	private String desc;
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOringl() {
		return oringl;
	}
	public void setOringl(String oringl) {
		this.oringl = oringl;
	}
	public String getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(String publicDate) {
		this.publicDate = publicDate;
	}

	
	public Webcontent getWebcontentType() {
		return webcontentType;
	}
	public void setWebcontentType(Webcontent webcontentType) {
		this.webcontentType = webcontentType;
	}
	public String[] getImages() {
		return images;
	}
	public void setImages(String[] images) {
		this.images = images;
	}

	
	
}
