package com.jinzht.web.test;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class User {
	@Size(min=3,max=5,message="{Pattern.user.name}")
	private String name;
	@NotNull(message="{Pattern.user.name}")
	private String telephone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
