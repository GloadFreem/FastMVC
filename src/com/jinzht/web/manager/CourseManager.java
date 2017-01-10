package com.jinzht.web.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jinzht.web.dao.ActionDAO;
import com.jinzht.web.dao.AttentionDAO;
import com.jinzht.web.dao.CollectionDAO;
import com.jinzht.web.dao.ContenttypeDAO;
import com.jinzht.web.dao.IdentiytypeDAO;
import com.jinzht.web.dao.LoginfailrecordDAO;
import com.jinzht.web.dao.RewardsystemDAO;
import com.jinzht.web.dao.RewardtradeDAO;
import com.jinzht.web.dao.SystemcodeDAO;
import com.jinzht.web.dao.UsercollectDAO;
import com.jinzht.web.dao.UsersDAO;
import com.jinzht.web.dao.WeburlrecordDAO;
import com.jinzht.web.entity.Action;
import com.jinzht.web.entity.Attention;
import com.jinzht.web.entity.Authentic;
import com.jinzht.web.entity.BusinessContentTypeDAO;
import com.jinzht.web.entity.BusinessInvitationCodeDAO;
import com.jinzht.web.entity.BusinessOrderDAO;
import com.jinzht.web.entity.BusinessSchoolDAO;
import com.jinzht.web.entity.BusinessVideo;
import com.jinzht.web.entity.BusinessVideoDAO;
import com.jinzht.web.entity.BusinessWeichatDAO;
import com.jinzht.web.entity.BusniessJoinDAO;
import com.jinzht.web.entity.BusniessTagDAO;
import com.jinzht.web.entity.Collection;
import com.jinzht.web.entity.Identiytype;
import com.jinzht.web.entity.Loginfailrecord;
import com.jinzht.web.entity.Project;
import com.jinzht.web.entity.Rewardsystem;
import com.jinzht.web.entity.SpeechmarkerDAO;
import com.jinzht.web.entity.Systemcode;
import com.jinzht.web.entity.Usercollect;
import com.jinzht.web.entity.Users;
import com.jinzht.web.entity.Weburlrecord;

public class CourseManager {
	private BusinessSchoolDAO buinessSchoolDao;
	private BusinessInvitationCodeDAO businessInvitationCodeDao;
	private BusniessJoinDAO BusniessJoinDao;
	private BusinessWeichatDAO businessWe3iChatDao;
	private BusinessVideoDAO businessVedioDao;
	private BusinessContentTypeDAO businessContentTypeDao;
	private BusniessTagDAO busniessTagDao;
	private SpeechmarkerDAO speechmarkerDAO;
	private BusinessOrderDAO businessOrderDao;

	/**
	 * @return the buinessSchoolDao
	 */
	public BusinessSchoolDAO getBuinessSchoolDao() {
		return buinessSchoolDao;
	}

	/**
	 * @param buinessSchoolDao
	 *            the buinessSchoolDao to set
	 */
	@Autowired
	public void setBuinessSchoolDao(BusinessSchoolDAO buinessSchoolDao) {
		this.buinessSchoolDao = buinessSchoolDao;
	}

	/**
	 * @return the businessInvitationCodeDao
	 */
	public BusinessInvitationCodeDAO getBusinessInvitationCodeDao() {
		return businessInvitationCodeDao;
	}

	/**
	 * @param businessInvitationCodeDao
	 *            the businessInvitationCodeDao to set
	 */
	@Autowired
	public void setBusinessInvitationCodeDao(
			BusinessInvitationCodeDAO businessInvitationCodeDao) {
		this.businessInvitationCodeDao = businessInvitationCodeDao;
	}

	/**
	 * @return the busniessJoinDao
	 */
	public BusniessJoinDAO getBusniessJoinDao() {
		return BusniessJoinDao;
	}

	/**
	 * @param busniessJoinDao
	 *            the busniessJoinDao to set
	 */
	@Autowired
	public void setBusniessJoinDao(BusniessJoinDAO busniessJoinDao) {
		BusniessJoinDao = busniessJoinDao;
	}

	public BusinessWeichatDAO getBusinessWe3iChatDao() {
		return businessWe3iChatDao;
	}

	@Autowired
	public void setBusinessWe3iChatDao(BusinessWeichatDAO businessWe3iChatDao) {
		this.businessWe3iChatDao = businessWe3iChatDao;
	}

	public BusinessVideoDAO getBusinessVedioDao() {
		return businessVedioDao;
	}
	@Autowired
	public void setBusinessVedioDao(BusinessVideoDAO businessVedioDao) {
		this.businessVedioDao = businessVedioDao;
	}

	public BusinessContentTypeDAO getBusinessContentTypeDao() {
		return businessContentTypeDao;
	}
	@Autowired
	public void setBusinessContentTypeDao(BusinessContentTypeDAO businessContentTypeDao) {
		this.businessContentTypeDao = businessContentTypeDao;
	}

	public BusniessTagDAO getBusniessTagDao() {
		return busniessTagDao;
	}
	@Autowired
	public void setBusniessTagDao(BusniessTagDAO busniessTagDao) {
		this.busniessTagDao = busniessTagDao;
	}

	public SpeechmarkerDAO getSpeechmarkerDAO() {
		return speechmarkerDAO;
	}
	@Autowired
	public void setSpeechmarkerDAO(SpeechmarkerDAO speechmarkerDAO) {
		this.speechmarkerDAO = speechmarkerDAO;
	}

	public BusinessOrderDAO getBusinessOrderDao() {
		return businessOrderDao;
	}
	@Autowired
	public void setBusinessOrderDao(BusinessOrderDAO businessOrderDao) {
		this.businessOrderDao = businessOrderDao;
	}

}
