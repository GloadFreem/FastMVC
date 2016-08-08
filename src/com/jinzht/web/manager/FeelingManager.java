package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.CityDAO;
import com.jinzht.web.dao.CommentDAO;
import com.jinzht.web.dao.ContentpriseDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.IndustoryareaDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.ProvinceDAO;
import com.jinzht.web.dao.PubliccontentDAO;
import com.jinzht.web.dao.ShareDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.City;
import com.jinzht.web.entity.Comment;
import com.jinzht.web.entity.Contentprise;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Industoryarea;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Publiccontent;
import com.jinzht.web.entity.Share;
import com.jinzht.web.entity.Sharetype;
import com.jinzht.web.entity.Users;

public class FeelingManager {

	private PubliccontentDAO publicContentDao;
	private ContentpriseDAO contentPriseDao;
	private ShareDAO shareDao;
	private CommentDAO commentDao;

	public Publiccontent findPublicContentById(Integer contentId) {
		return getPublicContentDao().findById(contentId);
	}

	/***
	 * 分页查询圈子信息
	 * 
	 * @param currentPage
	 *            当前页
	 * @return
	 */
	public List findFeelingByCursor(int currentPage, Integer userId) {
		List list = getPublicContentDao().findByCursor(currentPage);
		if (list != null && list.size() > 0) {
			Publiccontent content = null;
			for (int i = 0; i < list.size(); i++) {
				content = (Publiccontent) list.get(i);

				Users userPublic = new Users();

				Users user = content.getUsers();
				// 获取认证信息
				if (user.getAuthentics() != null
						&& user.getAuthentics().size() > 0) {
					Object[] authentices = user.getAuthentics().toArray();
					Authentic authentic = (Authentic) authentices[0];

					authentic.setAuthenticstatus(null);
					authentic.setIdentiytype(null);
					authentic.setIdentiyCarA(null);
					authentic.setIdentiyCarB(null);
					authentic.setIdentiyCarNo(null);
					authentic.setIntroduce(null);
					authentic.setAuthId(null);
					authentic.setBuinessLicence(null);
					authentic.setBuinessLicenceNo(null);
					authentic.setCompanyIntroduce(null);
					authentic.setAutrhrecords(null);
					authentic.setOptional(null);
					
					if(authentic.getName()==null || authentic.getName().equals(""))
					{
						String telephone = user.getTelephone();
						Integer length = telephone.length();
						String name = "用户"+user.getTelephone().substring(length-4, length);
						authentic.setName(name);
					}
					

					userPublic.setAuthentics(user.getAuthentics());
					userPublic.setUserId(user.getUserId());
					userPublic.setHeadSculpture(user.getHeadSculpture());
					userPublic.setName(authentic.getName());
				}

				content.setUsers(userPublic);

				// // 开始排除评论中不需要字段
				// if (content.getComments() != null
				// && content.getComments().size() > 0) {
				// Iterator<Comment> iterator = content.getComments()
				// .iterator();
				// while (iterator.hasNext()) {
				// Comment comment = iterator.next();
				//
				// Users temp = new Users();
				// user = comment.getUsersByUserId();
				// if (user.getAuthentics() != null) {
				// Object[] l = user.getAuthentics().toArray();
				// if (l.length > 0) {
				// Authentic authentic = (Authentic) l[0];
				// user.setName(authentic.getName());
				// } else {
				// user.setName("");
				// }
				//
				// } else {
				// user.setName("");
				// }
				//
				// temp.setAuthentics(null);
				// temp.setName(user.getName());
				// temp.setUserId(user.getUserId());
				// temp.setHeadSculpture(user.getHeadSculpture());
				// comment.setUsersByUserId(temp);
				//
				// temp = new Users();
				// user = comment.getUsersByAtUserId();
				// if (user != null && user.getAuthentics() != null) {
				// Object[] l = user.getAuthentics().toArray();
				// if (l.length > 0) {
				// Authentic authentic = (Authentic) l[0];
				// user.setName(authentic.getName());
				// } else {
				// user.setName("");
				// }
				//
				// temp.setAuthentics(null);
				// temp.setName(user.getName());
				// temp.setUserId(user.getUserId());
				// temp.setHeadSculpture(user.getHeadSculpture());
				//
				// comment.setUsersByAtUserId(temp);
				// }
				//
				// }
				// }

				// 开始排除点赞中不需要字段
				if (content.getContentprises() != null
						&& content.getContentprises().size() > 0) {
					Iterator<Contentprise> iterator = content
							.getContentprises().iterator();
					while (iterator.hasNext()) {
						Contentprise contentprise = iterator.next();
						Users temp = new Users();
						user = contentprise.getUsers();
						if (user.getAuthentics() != null) {
							Object[] l = user.getAuthentics().toArray();
							if (l.length > 0) {
								Authentic authentic = (Authentic) l[0];
								user.setName(authentic.getName());
							} else {
								user.setName("");
							}

						} else {
							user.setName("");
						}

						if (user.getUserId() == userId) {
							content.setFlag(true);
						}

						temp.setAuthentics(null);
						temp.setName(user.getName());
						temp.setUserId(user.getUserId());
						temp.setHeadSculpture(user.getHeadSculpture());

						contentprise.setUsers(temp);

						Integer userInstanceId = contentprise.getUsers()
								.getUserId();

						if (userInstanceId.equals(userId)) {
							content.setFlag(true);
						}
					}
				}

				// 转发量
				Sharetype type = new Sharetype();
				type.setShareTypeId(2);

				Map map = new HashMap();
				map.put("sharetype", type);
				map.put("contentId", content.getPublicContentId());

				// 获取转发量
				Integer count = getShareDao().counterByProperties(map);
				content.setShareCount(count);
				content.setPriseCount(content.getContentprises().size());
				content.setCommentCount(content.getComments().size());

				content.setComments(null);
				content.setContentprises(null);
			}

		}

		return list;
	}

	/***
	 * 圈子
	 * 
	 * @param feelingId
	 * @return
	 */
	public Publiccontent findFeelingById(Integer feelingId, Integer userId) {
		Publiccontent content = getPublicContentDao().findById(feelingId);
		Users userPublic = new Users();

		Users user = content.getUsers();
		// 获取认证信息
		if (user.getAuthentics() != null && user.getAuthentics().size() > 0) {
			Object[] authentices = user.getAuthentics().toArray();
			Authentic authentic = (Authentic) authentices[0];

			authentic.setAuthenticstatus(null);
			authentic.setIdentiytype(null);
			authentic.setIdentiyCarA(null);
			authentic.setIdentiyCarB(null);
			authentic.setIdentiyCarNo(null);
			authentic.setIntroduce(null);
			authentic.setAuthId(null);
			authentic.setBuinessLicence(null);
			authentic.setBuinessLicenceNo(null);
			authentic.setCompanyIntroduce(null);
			authentic.setAutrhrecords(null);
			authentic.setOptional(null);

			userPublic.setAuthentics(user.getAuthentics());
			userPublic.setUserId(user.getUserId());
			userPublic.setHeadSculpture(user.getHeadSculpture());
			userPublic.setName(authentic.getName());
		}

		content.setUsers(userPublic);

		// 开始排除评论中不需要字段
		if (content.getComments() != null && content.getComments().size() > 0) {
			Iterator<Comment> iterator = content.getComments().iterator();
			while (iterator.hasNext()) {
				Comment comment = iterator.next();

				Users temp = new Users();
				user = comment.getUsersByUserId();
				if (user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

				} else {
					user.setName("");
				}

				temp.setAuthentics(null);
				temp.setName(user.getName());
				temp.setUserId(user.getUserId());
				temp.setHeadSculpture(user.getHeadSculpture());
				comment.setUsersByUserId(temp);

				temp = new Users();
				user = comment.getUsersByAtUserId();
				if (user != null && user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

					temp.setAuthentics(null);
					temp.setName(user.getName());
					temp.setUserId(user.getUserId());
					temp.setHeadSculpture(user.getHeadSculpture());

					comment.setUsersByAtUserId(temp);
				}

			}
		}

		// 开始排除点赞中不需要字段
		if (content.getContentprises() != null
				&& content.getContentprises().size() > 0) {
			Iterator<Contentprise> iterator = content.getContentprises()
					.iterator();
			while (iterator.hasNext()) {
				Contentprise contentprise = iterator.next();
				Users temp = new Users();
				user = contentprise.getUsers();
				
				if(user.getUserId().equals(userId))
				{
					content.setFlag(true);
				}
//				if (user.getAuthentics() != null) {
//					Object[] l = user.getAuthentics().toArray();
//					if (l.length > 0) {
//						Authentic authentic = (Authentic) l[0];
//						user.setName(authentic.getName());
//					} else {
//						user.setName("");
//					}
//
//				} else {
//					user.setName("");
//				}

				if (user.getUserId() == userId) {
					content.setFlag(true);
				}

				temp.setAuthentics(null);
				temp.setName(user.getName());
				temp.setUserId(user.getUserId());
				temp.setHeadSculpture(user.getHeadSculpture());
				//
				// // user.setAuthentics(null);
				// user.setUserstatus(null);
				// user.setTelephone(null);
				// user.setPassword(null);
				// user.setPlatform(null);
				// user.setLastLoginDate(null);
				contentprise.setUsers(temp);
			}
		}

		// 转发量
		Sharetype type = new Sharetype();
		type.setShareTypeId(2);

		Map map = new HashMap();
		map.put("sharetype", type);
		map.put("contentId", content.getPublicContentId());

		// 获取转发量
		Integer count = getShareDao().counterByProperties(map);
		content.setShareCount(count);
		content.setPriseCount(content.getContentprises().size());
		content.setCommentCount(content.getComments().size());

		return content;
	}

	/***
	 * 分页查询评论列表
	 * 
	 * @param page
	 * @param feelingId
	 * @return
	 */
	public List findFeelingCommentByPage(Integer page, Integer feelingId) {
		Publiccontent content = this.findPublicContentById(feelingId);

		List list = null;
		list = getCommentDao().findByPropertyByPage("publiccontent", content,
				page);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Comment comment = (Comment) list.get(i);

				Users temp = new Users();
				Users user = comment.getUsersByUserId();
				if (user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

				} else {
					user.setName("");
				}

				temp.setAuthentics(null);
				temp.setName(user.getName());
				temp.setUserId(user.getUserId());
				temp.setHeadSculpture(user.getHeadSculpture());
				comment.setUsersByUserId(temp);

				temp = new Users();
				user = comment.getUsersByAtUserId();
				if (user != null && user.getAuthentics() != null) {
					Object[] l = user.getAuthentics().toArray();
					if (l.length > 0) {
						Authentic authentic = (Authentic) l[0];
						user.setName(authentic.getName());
					} else {
						user.setName("");
					}

					temp.setAuthentics(null);
					temp.setName(user.getName());
					temp.setUserId(user.getUserId());
					temp.setHeadSculpture(user.getHeadSculpture());

					comment.setUsersByAtUserId(temp);
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
	 * 
	 * @param content
	 */
	public void saveOrUpdate(Publiccontent content) {
		getPublicContentDao().saveOrUpdate(content);
	}

	/***
	 * 取消点赞
	 * 
	 * @param prise
	 */
	public void cancelPrise(Contentprise prise) {
		getContentPriseDao().delete(prise);
	}

	/***
	 * 获取分享记录
	 * @param shareId
	 * @return
	 */
	public Share findShareFeelingById(Integer shareId)
	{
		return getShareDao().findById(shareId);
	}
	
	/***
	 * 更新分享记录
	 * @param share
	 */
	public void updateShare(Share share)
	{
		getShareDao().saveOrUpdate(share);
	}
	
	/***
	 * 删除圈子
	 * @param contentId
	 */
	public void deletePublicContent(Integer contentId)
	{
		Publiccontent content = getPublicContentDao().findById(contentId);
		
		//删除
		getPublicContentDao().delete(content);
	}
	
	public void deletePublicContentComment(Integer commentId)
	{
		Comment comment = getCommentDao().findById(commentId);
		getCommentDao().delete(comment);
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

	public ShareDAO getShareDao() {
		return shareDao;
	}

	@Autowired
	public void setShareDao(ShareDAO shareDao) {
		this.shareDao = shareDao;
	}

	public CommentDAO getCommentDao() {
		return commentDao;
	}

	@Autowired
	public void setCommentDao(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}

}
