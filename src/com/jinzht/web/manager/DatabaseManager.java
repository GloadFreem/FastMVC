package com.jinzht.web.manager;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.tools.DateUtils;
import com.jinzht.tools.FileUtil;
import com.jinzht.web.dao.ProjectDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.database.move.DatabaseMove;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.Authenticstatus;
import com.jinzht.web.entity.Financestatus;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Userstatus;

public class DatabaseManager {
	private ProjectDAO projectDao;
	private DatabaseMove move;
	private UsersDAO userDao;
	
	public DatabaseManager()
	{
		this.move = new DatabaseMove();
		move.setRequestUrl("databasemove");
	}
	/***
	 * 迁移项目表
	 * @param table
	 * @param page
	 * @return
	 */
	public JSONArray moveProjectTable(String table,Integer page,Integer size)
	{
		move.setPage(page);
		move.setSize(size);
		move.setTableName(table);
		
		JSONArray dataArray = move.goRequestData();
		
		if(dataArray!=null && dataArray.size()>0)
		{
			System.out.println("返回记录条数:"+dataArray.size());
			for(int i = 0;i<dataArray.size();i++)
			{
				JSONObject item = dataArray.getJSONObject(i);
				Users user = new Users();
				short os = (short)item.getInt("os");
				user.setExtUserId(item.getInt("userId"));
				user.setName(item.getString("name"));
				user.setTelephone(item.getString("tel"));
				user.setPassword(item.getString("passwd"));
				user.setHeadSculpture(item.getString("photo"));
				user.setWechatId(item.getString("openid"));
				user.setPlatform(os);
				user.setRegId(item.getString("regid"));
				
				Userstatus userStatus = new Userstatus();
				userStatus.setUserStatusId(1);
						
						
				user.setUserstatus(userStatus);
				

				String dateStr = item.getString("lastlogin");
				Date date;
				try {
					if(dateStr!=null && dateStr.equals(""))
					{
						date = DateUtils.stringToDate(dateStr, DateUtils.DATETIME_FORMAT);
						user.setLastLoginDate(date);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				Authentic authentic = new Authentic();
				authentic.setUsers(user);
				authentic.setName(item.getString("name"));
				authentic.setIdentiyCarNo(item.getString("idno"));
				authentic.setCompanyName(item.getString("company"));
				authentic.setPosition(item.getString("position"));
				authentic.setCompanyAddress(item.getString("addr"));
				authentic.setIdentiyCarA(item.getString("idpic"));
				authentic.setOptional(item.getString("qualification"));
				authentic.setIntroduce(item.getString("profile"));
				
				short t = 2;
				Identiytype type = new Identiytype();
				type.setIdentiyTypeId(t);
				authentic.setIdentiytype(type);
				
				Authenticstatus status = new Authenticstatus();
				if(!item.get("valid").equals(null))
				{
//					System.out.println(item.get("valid"));
					boolean flag = item.getBoolean("valid");
					if(flag)
					{
						status.setStatusId(8);
					}else{
						status.setStatusId(6);
					}
				}else{
					status.setStatusId(6);
				}
				
				authentic.setAuthenticstatus(status);
				
				Set set = new HashSet();
				set.add(authentic);
				user.setAuthentics(set);
				
				getUserDao().save(user);
				
			}
		}
		
//		if(dataArray!=null && dataArray.size()>0)
//		{
//			for(int i = 0;i<dataArray.size();i++)
//			{
//				JSONObject item = dataArray.getJSONObject(i);
//				Project project = new Project();
//				project.setBorrowerUserNumber(item.getString("brrow_user_no"));
//				project.setAbbrevName(item.getString("abbrevname"));
//				project.setFullName(item.getString("company"));
//				project.setStartPageImage(item.getString("img"));
//				
//				Financestatus status = new Financestatus();
//				status.setStatusId(1);
//				project.setFinancestatus(status);
//				
//				getProjectDao().save(project);
//			}
//		}
		return dataArray;
	}
	public JSONArray moveProjectTableData(String table,Integer page)
	{
		String fileName = "C:/Users/Administrator/Desktop/data.txt";
		String jsonString =  FileUtil.readFileByBytes(fileName);  
		JSONObject json= JSONObject.fromObject(jsonString);
		JSONArray array = json.getJSONArray("data");
		
		return array;
	}

	public ProjectDAO getProjectDao() {
		return projectDao;
	}
	@Autowired
	public void setProjectDao(ProjectDAO projectDao) {
		this.projectDao = projectDao;
	}
	public UsersDAO getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UsersDAO userDao) {
		this.userDao = userDao;
	}
	

}
