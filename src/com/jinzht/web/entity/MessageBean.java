package com.jinzht.web.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class MessageBean {
	@NotNull(message="{Pattern.messagebean.telephone.notnull}")
	@Length(min=11,max=11,message="{Pattern.messagebean.telephone.error}")
	@Pattern(regexp="^((1[3,5,8][0-9])|(14[5,7])|(17[,0,6,7,8]))\\d{8}$", message="{Pattern.messagebean.telephone.pattern}")
	private String telephone;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
