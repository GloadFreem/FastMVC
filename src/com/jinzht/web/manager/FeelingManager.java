package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.IndustorytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Industorytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Users;

public class FeelingManager {

	private PubliccontentDAO publicContentDao;
	private ContentpriseDAO contentPriseDao;

	public Publiccontent findPublicContentById(Integer contentId){
		return getPublicContentDao().findById(contentId);
	}
	/***
	 * 分页查询圈子信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public List findFeelingByCursor(int currentPage) {
		List list = getPublicContentDao().findByCursor(currentPage);
		if (list != null && list.size() > 0) {
			Publiccontent content = null;
			for (int i = 0; i < list.size(); i++) {
				content = (Publiccontent) list.get(i);
				
				//开始排除评论中不需要字段
				if(content.getComments()!=null && content.getComments().size()>0){
					Iterator<Comment> iterator = content.getComments().iterator();
					while(iterator.hasNext()){
						Comment comment = iterator.next();
						Users user = comment.getUsersByUserId();
						if(user.getAuthentics()!=null){
							Object[] l = user.getAuthentics().toArray();
							if(l.length>0){
								Authentic authentic = (Authentic) l[0];
								user.setName(authentic.getName());
							}else{
								user.setName("");
							}
							
						}else{
							user.setName("");
						}
						user.setAuthentics(null);
						user.setUserstatus(null);
						user.setTelephone(null);
						user.setPassword(null);
						user.setPlatform(null);
						user.setLastLoginDate(null);
						comment.setUsersByUserId(user);
					}
				}
				
				//开始排除点赞中不需要字段
				if(content.getContentprises()!=null && content.getContentprises().size()>0){
					Iterator<Contentprise> iterator = content.getContentprises().iterator();
					while(iterator.hasNext()){
						Contentprise contentprise = iterator.next();
						Users user = contentprise.getUsers();
						if(user.getAuthentics()!=null){
							Object[] l = user.getAuthentics().toArray();
							if(l.length>0){
								Authentic authentic = (Authentic) l[0];
								user.setName(authentic.getName());
							}else{
								user.setName("");
							}
							
						}else{
							user.setName("");
						}
						user.setAuthentics(null);
						user.setUserstatus(null);
						user.setTelephone(null);
						user.setPassword(null);
						user.setPlatform(null);
						user.setLastLoginDate(null);
						contentprise.setUsers(user);
					}
				}
			}
		}

		return list;
	}

	/***
	 * 添加圈子记录
	 * 
	 * @param content
	 *            圈子记录
	 * @return
	 */
	public void addPublicContent(Publiccontent content) {
		getPublicContentDao().save(content);
	}
	
	/**
	 * 更新圈子
	 * @param content
	 */
	public void saveOrUpdate(Publiccontent content){
		getPublicContentDao().saveOrUpdate(content);
	}
	
	/***
	 * 取消点赞
	 * @param prise
	 */
	public void cancelPrise(Contentprise prise)
	{
		 getContentPriseDao().delete(prise);
	}

	public PubliccontentDAO getPublicContentDao() {
		return publicContentDao;
	}

	@Autowired
	public void setPublicContentDao(PubliccontentDAO publicContentDao) {
		this.publicContentDao = publicContentDao;
	}
	public ContentpriseDAO getContentPriseDao() {
		return contentPriseDao;
	}
	@Autowired
	public void setContentPriseDao(ContentpriseDAO contentPriseDao) {
		this.contentPriseDao = contentPriseDao;
	}

}
