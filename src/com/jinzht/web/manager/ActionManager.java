package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.ActionDAO;
import com.jinzht.web.dao.ActioncommentDAO;
import com.jinzht.web.dao.ActionimagesDAO;
import com.jinzht.web.dao.ActionpriseDAO;
import com.jinzht.web.dao.AttentionDAO;
import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.IndustorytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actionprise;
import com.jinzht.web.entity.Attention;
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

public class ActionManager {

	private PubliccontentDAO publicContentDao;
	private ActionpriseDAO actionPriseDao;
	private AttentionDAO attentionDao;
	private ActionDAO actionDao;
	private ActionimagesDAO actionImageDao;
	private ActioncommentDAO actionCommentDao;

	/***
	 * 根据id获取活动
	 * @param contentId
	 * @return
	 */
	public Action findActionById(Integer contentId){
		return getActionDao().findById(contentId);
	}
	/***
	 * 分页查询活动信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public List findActionByCursor(int currentPage,Users user) {
		List list = getActionDao().findByCursor(currentPage);
		if (list != null && list.size() > 0) {
			Action action = null;
			for (int i = 0; i < list.size(); i++) {
				action = (Action) list.get(i);
				
				Attention attention = this.findAttentionByActionIdAndUser(action, user);
				if(attention!=null)
				{
					short flag = 1;
					action.setFlag(flag);
				}
			}
		}

		return list;
	}
	
	/***
	 * 根据活动，参加用户获取报名对象
	 * @param action
	 * @param user
	 * @return
	 */
	public Attention findAttentionByActionIdAndUser(Action action,Users user)
	{
		List list =  getAttentionDao().findByProperty("users", user);
		Attention attention;
		if(list!=null && list.size()>0){
			for(int i = 0;i<list.size();i++){
				attention = (Attention) list.get(0);
				if(attention.getAction().getActionId() == action.getActionId()){
					return attention;
				}
			}
		}
		
		return null;
	}
	public Actionprise findPriseByActionIdAndUser(Action action,Users user)
	{
		List list =  getActionPriseDao().findByProperty("users", user);
		Actionprise prise;
		if(list!=null && list.size()>0){
			for(int i = 0;i<list.size();i++){
				prise = (Actionprise) list.get(0);
				if(prise.getAction().getActionId() == action.getActionId()){
					return prise;
				}
			}
		}
		
		return null;
	}

	/***
	 * 添加圈子记录
	 * 
	 * @param content
	 *            圈子记录
	 * @return
	 */
	public void addAttention(Attention attention) {
		getAttentionDao().save(attention);
	}
	
	/**
	 * 更新活动
	 * @param content
	 */
	public void saveOrUpdate(Action action){
		getActionDao().saveOrUpdate(action);
	}
	
	/***
	 * 取消点赞
	 * @param prise
	 */
	public void cancelPrise(Actionprise prise)
	{
		 getActionPriseDao().delete(prise);
	}
	
	/***
	 * 获取活动图片
	 * @param action
	 * @return
	 */
	public List findImagesListByAction(Action action){
		return getActionImageDao().findByProperty("action", action);
	}
	
	/***
	 * 获取所有报名人数
	 * @param action
	 * @return
	 */
	
	public List findAttentionListByAction(Action action,Integer page){
		return getAttentionDao().findByPropertyWithPage("action", action,page);
	}
	
	/***
	 * 获取所有评论
	 * @param action
	 * @return
	 */
	public List findCommentListByAction(Action action,Integer page){
		return getActionCommentDao().findByPropertyWithPage("action", action,page);
	}
	
	/***
	 * 获取所有点赞
	 * @param action
	 * @return
	 */
	public List findPriseListByAction(Action action){
		return getActionPriseDao().findByProperty("action", action);
	}
//	public Users findUserByAttention(Attention attention)
//	{
//		
//	}

	public PubliccontentDAO getPublicContentDao() {
		return publicContentDao;
	}

	@Autowired
	public void setPublicContentDao(PubliccontentDAO publicContentDao) {
		this.publicContentDao = publicContentDao;
	}
	public ActionDAO getActionDao() {
		return actionDao;
	}
	@Autowired
	public void setActionDao(ActionDAO actionDao) {
		this.actionDao = actionDao;
	}
	public ActionpriseDAO getActionPriseDao() {
		return actionPriseDao;
	}
	@Autowired
	public void setActionPriseDao(ActionpriseDAO actionPriseDao) {
		this.actionPriseDao = actionPriseDao;
	}
	public AttentionDAO getAttentionDao() {
		return attentionDao;
	}
	@Autowired
	public void setAttentionDao(AttentionDAO attentionDao) {
		this.attentionDao = attentionDao;
	}
	public ActionimagesDAO getActionImageDao() {
		return actionImageDao;
	}
	@Autowired
	public void setActionImageDao(ActionimagesDAO actionImageDao) {
		this.actionImageDao = actionImageDao;
	}
	public ActioncommentDAO getActionCommentDao() {
		return actionCommentDao;
	}
	@Autowired
	public void setActionCommentDao(ActioncommentDAO actionCommentDao) {
		this.actionCommentDao = actionCommentDao;
	}

}
