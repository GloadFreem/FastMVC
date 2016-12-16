package com.jinzht.web.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.jinzht.web.dao.ActionintroduceDAO;
import com.jinzht.web.dao.ActionpriseDAO;
import com.jinzht.web.dao.AttentionDAO;
import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Actioncomment;
import com.jinzht.web.entity.Actionprise;
import com.jinzht.web.entity.Attention;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Users;

public class ActionManager {

	private PubliccontentDAO publicContentDao;
	private ActionpriseDAO actionPriseDao;
	private AttentionDAO attentionDao;
	private ActionDAO actionDao;
	private ActionimagesDAO actionImageDao;
	private ActioncommentDAO actionCommentDao;
	private ActionintroduceDAO actionIntroduceDao;

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
	public List findActionByCursor(int currentPage,Integer version) {
		List list = getActionDao().findByCursor(currentPage);
		if (list != null && list.size() > 0) {
			Action action = null;
			for (int i = 0; i < list.size(); i++) {
				action = (Action) list.get(i);
				Attention attention = this.findAttentionByActionIdAndUser(action,null);
				if(attention!=null)
				{
					attention.setUsers(null);
					action.setAttended(true);
				}
				
				Actionprise prise = this.findActionPrise(action);
				if(prise!=null){
					action.setFlag(true);
				}
				
				action.setAttentions(null);
				action.setActionprises(null);
				action.setActionimages(null);
				action.setDescription(null);
				action.setActionintroduces(null);

			}
		}

		return list;
	}
	
	public Integer findUserIdByCommentId(Actioncomment comment)
	{
		List list =  getActionCommentDao().findUserIdByCommentId(comment.getCommentId());
		if(list!=null && list.size()>0)
		{
			return (Integer)list.get(0);
		}
		return null;
		
	}
	
	public Integer findAtUserIdByCommentId(Actioncomment comment)
	{
		List list =  getActionCommentDao().findAtUserIdByCommentId(comment.getCommentId());
		if(list!=null && list.size()>0)
		{
			return (Integer)list.get(0);
		}
		return null;
		
	}
	/***
	 * 根据关键词搜索huodong
	 * @param keyWord
	 * @param page
	 * @return
	 */
	public List findActionByKeyWord(String keyWord,Integer page)
	{
		List list = new ArrayList();
		List reqList = new ArrayList();
		reqList.add("name");
		reqList.add("address");
		reqList.add("description");
		
		for(Object str :reqList)
		{
			List l  = getActionDao().findByKeyWord(keyWord, page,str.toString());
			for(Object item :l)
			{
				list.add(item);
			}
		}
		return list;
	}
	
	
	public List findProjectByName(String name)
	{
		List<Action> projects = getActionDao().findByName(name);
		return projects;
	}
	public Actionprise findActionPrise(Action action)
	{
		Actionprise prise = null;
		
		//参数封装
		Map map  = new HashMap();
		map.put("action", action);
		
		List list = getActionPriseDao().findByProperties(map, 0);
		if(list!=null && list.size()>0){
			prise = (Actionprise)list.get(0);
		}
		return prise;
	}
	
	/***
	 * 根据活动，参加用户获取报名对象
	 * @param action
	 * @param user
	 * @return
	 */
	public Attention findAttentionByActionIdAndUser(Action action,Users user)
	{
		List list =  getAttentionDao().findAttentionByUserIdAndActionId(action.getActionId(),user);
		Attention attention;
		if(list!=null && list.size()>0){
			return (Attention) list.get(0);
		}
		
		return null;
	}
	
	public Actionprise findPriseByActionIdAndUser(Action action,Users user)
	{
		Actionprise prise =getActionPriseDao().findByActionIdAndUserId(action.getActionId(), user.getUserId());
		return prise;
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
	
	public void saveOpUpdateActionComment(Actioncomment comment)
	{
		getActionCommentDao().save(comment);
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
		return getAttentionDao().findByAttendWithActionPage(action.getActionId(),page);
	}
	
	/***
	 * 获取所有评论
	 * @param action
	 * @return
	 */
	public List findCommentListByAction(Action action,Integer page,int platform){
		List list =  getActionCommentDao().findByPropertyWithPage("action", action,page);
		
		if(list!=null && list.size()>0)
		{
			for(int  i = 0;i<list.size();i++)
			{
				Actioncomment comment = (Actioncomment)list.get(i);
				//兼容表情内容
				String c = comment.getContent();
				if(platform!=0)
				{
					
					try {
						c=URLEncoder.encode(c, "utf-8");
						comment.setContent(c);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}
	
	/***
	 * 获取所有点赞
	 * @param action
	 * @return
	 */
	public List findPriseListByAction(Action action){
		return getActionPriseDao().findByAction("action", action);
	}
	public Integer findUserByAttention(Attention attention)
	{
		List list = getAttentionDao().findUserIdByAttentionId(attention.getAttendUid());
		if(list!=null && list.size()>0)
		{
			return (Integer) list.get(0);
		}
		
		return null;
	}
	
	/***
	 * 根据点赞记录id获取用户id
	 * @param prise
	 * @return
	 */
	public Integer findActionPriseUserId(Actionprise prise)
	{
		Users user = null;
		List list  = getActionPriseDao().findUsersIdByPriseId(prise.getPriseId());
		if(list!=null && list.size()>0)
		{
			return (Integer)list.get(0);
		}
		return null;
	}
	
	/***
	 * 删除活动评论
	 * @param commentId
	 */
	public void deleteActionCommentByCommentId(Integer commentId)
	{
		Actioncomment comment = getActionCommentDao().findById(commentId);
		
		getActionCommentDao().delete(comment);
	}

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
	public ActionintroduceDAO getActionIntroduceDao() {
		return actionIntroduceDao;
	}
	@Autowired
	public void setActionIntroduceDao(ActionintroduceDAO actionIntroduceDao) {
		this.actionIntroduceDao = actionIntroduceDao;
	}

}
