package com.jinzht.tools;

public class OauthBean {

//	{
//		"access_token": "_vZ-5NhwdGVBf0wP60CazQIIa1yOywoYhqhCN_VXYxUuPPtoFsq_kTYo-quxi6zbiUAHQQ0pRK8J3cCcYMUHgVXIHrQbLFGw6pO_Ykg3J3w",
//		"expires_in": 7200,
//		"refresh_token": "Zz4nJwYqUNBcNHHMSEg2tdcFF7T7H2ah41R9g2gB8gAXgB4nlb3hRIAaGRYbWsJ0Kq4STZ485UhZvCaZpSFnrr3rrIH_04aMS4dX8DeCit4",
//		"openid": "oF91TwNE81n50ktLLysamSpjzumQ",
//		"scope": "snsapi_base",
//		"unionid": "oycRDs6DOEMWqQ7CX3oSr7VixAEc"
//		}
//	
//	{
//		"errcode": 40029,
//		"errmsg": "invalid code, hints: [ req_id: 4ObujA0558ns86 ]"
//		}

	private String access_token;
	private String expires_in;
	private String refresh_token;
	private String openid;
	private String scope;
	private String unionid;
	
	private String errcode;
	private String errmsg;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
	
	
}
