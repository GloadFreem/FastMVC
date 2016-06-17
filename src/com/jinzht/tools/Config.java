package com.jinzht.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Config {
	public static String SMS_USERID = "1012888";
	public static String SMS_ACCOUNT = "dlbjjztx";
	public static String SMS_PASSWORD = "pBJ0SiR6";
	public static String SMS_VERIFY_CODE = "验证码 %d , 十分钟内有效【金指投投融资】";
	public static String SMS_VERIFY_STRING = "【金指投投融资】";
	public static String SMS_HAVE_SEND_STRING = "验证码发送成功，请注意查收!";
	public static String SMS_FAIL_SEND_STRING = "验证码发送失败，请稍后重试!";
	
	
	
	//登录常量
	public static String  STRING_LOGING_STATUS_ONLINE = "用戶已登录！";
	public static String  STRING_LOGING_STATUS_OFFLINE = "用户未登录！";
	public static String  STRING_LOGING_OUT = "注销登录成功！";
	public static String  STRING_LOGING_FAIL = "登录失败！";
	public static String  STRING_LOGING_SUCCESS = "登录成功！";
	public static String  STRING_LOGING_TIP = "未登录，请先登录应用！";
	public static String  STRING_LOGING_WECHAT_FAIL = "微信登录失败！";
	public static String  STRING_LOGING_CODE_NOT_GET = "请先获取验证码!";
	public static String  STRING_LOGING_WECHAT_SUCCESS = "微信登录成功！";
	public static String  STRING_LOGING_CODE_NOT_NULL = "验证码不能为空！";
	public static String  STRING_LOGING_TEL_NOT_NULL = "手机号码不能为空！";
	public static String  STRING_LOGING_PASSWORD_NOT_NULL = "密码不能为空！";
	public static String  STRING_LOGING_FAIL_NO_USER = "用户尚未注册，请先注册！";
	public static String  STRING_LOGING_WECHATID_NOT_NULL = "微信ID不能为空！";
	public static String  STRING_LOGING_CODE_ERROR = "验证码输入错误，请重新输入！";
	public static String  STRING_LOGING_FAIL_ERROR_PASS = "登录账号或者密码错误！";
	public static String  STRING_LOGING_FAIL_LEFT = "密码错误已超过%d次,还剩%d次重试机会！";
	public static String  STRING_LOGING_FAIL_MAX = "已达到当日密码输入错误最大次数，请联系客服！";
	public static String  STRING_LOGING_FAIL_HAS_REGISTED = "手机号码已注册，请直接登录！";
	
	//身份认证
	public static String  STRING_AUTH_IDENTIY_TYPE_NOT_NULL = "用户身份不能为空!";
	public static String  STRING_AUTH_IDENTIY_SUCCESS = "投资人身份添加成功!";
	public static String  STRING_AUTH_CONFIRM_FAIL = "无法修改认证中身份信息!";
	public static String  STRING_AUTH_IDENTIY_FAIL = "投资人身份添加失败!";
	public static List<String> STRING_AUTH_QUALIFICATION = Arrays.asList("'(一)《私募投资基金监督管理暂行办法》规定的合格投资者'", 
			"'(二)投资单个融资项目的最低金额不低于100万元人民币的单位或个人", 
			"'(三)社会保障基金、企业年金等养老基金，慈善基金等社会公益基金，以及依法设立并在中国证券投资基金业协会备案的投资计划",
			"'(四)净资产不低于1000万元人民币的单位",
			"'(五)金融资产不低于300万元人民币或最近三年个人年均收入不低于50万元人民币的个人。上述个人除能提供相关财产、收入证明外，还应当能辨识、判断和承担相应投资风险",
			"'(六)证券业协会规定的其他投资者");
	public static String STRING_AUTH_SUBMMIT_SUCCESS = "认证信息提交成功";
	public static String STRING_AUTH_PARAM_NAME_NOT_NULL = "用户真实姓名不能为空!";
	public static String STRING_AUTH_PARAM_IDENTIYCARA_NOT_NULL = "身份证正面照片不能为空!";
	public static String STRING_AUTH_PARAM_IDENTIYCARB_NOT_NULL = "身份证反面照片不能为空!"; 
	public static String STRING_AUTH_HAS = "该身份已认证通过，无需重新认证!"; 
	public static HashMap<String,Integer> STRING_AUTH_STATUS = new HashMap<String,Integer>() {
	    {
	        put( "未认证",0); 
	        put( "认证中",1); 
	        put( "认证失败",2); 
	        put( "认证通过",3); 
	    }
	};

	
	//注册常量
	public static String STRING_REGIST_SUCCESS = "恭喜您,注册成功！";
	public static String STRING_REGIST_FAIL = "注册失败，请重新尝试！";
	
	//密码
	public static String STRING_PASSWORD_RESET_SUCCESS ="密码重置成功！";
	public static String STRING_PASSWORD_RESET_FAIL ="密码失败！";

	
	//短信模板
	public static String  STRING_SMS_REGISTE = "感谢你注册金指投--中国成长型企业股权投融资平台";
	public static String  STRING_SMS_ROADSHOW_SUBMIT = "尊贵的金指投用户, 你的项目申请提交成功。根据您项目的实际情况, 会有专门的顾问和你取得联系, 请保持手机畅通。如有疑问请致电 18691883712";
	public static String  STRING_SMS_ROADSHOW_VALID_TRUE = "尊贵的金指投用户，您的路演申请提交成功，已进入项目审核阶段，我们的工作人员将会在第一时间联系您，请保持手机畅通。您可以打开【金指投】APP->个人中心->进度查看->我的路演 查看详情，感谢您长期以来的支持！";
	public static String  STRING_SMS_ROADSHOW_VALID_FALSE = "尊贵的金指投用户, 您的路演申请未通过审核。请打开【金指投】APP->个人中心->进度查看->我的路演 查看原因。请您及时修改、完善信息并重新提交申请。如有疑问请致电 18691883712";
	public static String  STRING_SMS_INVESTOR_SUBMIT = "尊贵的金指投用户，恭喜您的“VIP投资人认证”提交成功。您可以打开【金指投】APP->个人中心->进度查看->投资人认证 查看详情。如有疑问请致电 18691883712";
	public static String  STRING_SMS_AUTH_TRUE  = "尊贵的金指投用户，恭喜您的“VIP投资人认证”通过审核，小金将于后续贴身为您服务。使用金指投APP，开启您的投融资之旅吧！如有疑问请致电 18691883712";
	public static String  STRING_SMS_AUTH_FALSE = "尊贵的金指投用户，小金抱歉的通知您，您的“VIP投资人认证”未通过审核。如有疑问请致电 18691883712";
	public static String  STRING_SMS_PARTICIPATE_SUBMIT = "尊贵的金指投用户，您的[%s]来现场申请已提交。请打开【金指投】APP->个人中心->进度查看->来现场 查看详情。如有疑问请致电 18691883712";
	public static String  STRING_SMS_PARTICIPATE_VALID_TRUE = "尊贵的金指投用户，您于[%s]申请参加[%s]来现场申请通过审核。打开【金指投】APP->个人中心->进度查看->来现场 查看详情。如有疑问请致电 18691883712";
	public static String  STRING_SMS_PARTICIPATE_VALID_FALSE = "尊贵的金指投用户，您于[%s]申请参加[%s]来现场申请未通过审核。 请打开【金指投】APP->个人中心->进度查看->来现场 查看详情。如有疑问请致电 18691883712";
	public static String  STRING_SMS_INVEST_VALID_TRUE = "尊贵的金指投用户，您于[%s]投资[%s][%s]万。如有问题请致电 18681838312, 或致邮 kf@jinzht.com。我们的工作人员将会第一时间联系您，请您保持手机畅通。您也可以打开【金指投】APP->个人>中心->我的投融资->我投资的项目 查看详情。";
	
	//系统
//	public static String STRING_SYSTEM_ADDRESS ="http://192.168.5.122:8080/jinzht/";
	public static String STRING_SYSTEM_ADDRESS ="http://www.jinzht.com:8080/jinzht/";
	public static String STRING_SYSTEM_INTRODUCE ="http://192.168.5.122:8080/jinzht/";
	public static String  STRING_USER_HEADER_PICTURE_FORMAT = "jinzht_user_%d";
	public static String  STRING_USER_HEADER_PICTURE_UPDATE_SUCCESS = "头像更新成功！";
	public static String  STRING_USER_HEADER_PICTURE_UPDATE_FAIL = "头像更新失败！";
	public static String  STRING_USER_COMPNY_UPDATE_SUCCESS = "公司更新成功！";
	public static String  STRING_USER_POSITION_UPDATE_SUCCESS = "职位更新成功！";
	public static String  STRING_USER_ADDRESS_UPDATE_SUCCESS = "地址更新成功！";
	public static String  STRING_USER_TELEPHONE_UPDATE_SUCCESS = "手机绑定成功！";
	public static String  STRING_USER_PASSWORD_COMPARE_FAIL = "旧密码输入错误，请重新输入！";
	public static String  STRING_USER_PASSWORD_UPDATE_SUCCESS = "重新设置密码成功！";
	public static String  STRING_USER_IDENTITY_PICTUREA_FORMAT = "jinzht_user_identiy_a_%d";
	public static String  STRING_USER_IDENTITY_PICTUREB_FORMAT = "jinzht_user_identiy_b_%d";
	public static String  STRING_USER__IDENTITY_BUINESS_FORMAT = "jinzht_user_identiy_buiness_%d";
	
	//站内信
	public static String STRING_SYSTEM_MESSAGE_DELETE_SUCCESS ="信息删除成功！";
	public static String STRING_SYSTEM_MESSAGE_DELETE_FAIL ="信息删除失败！";
	public static String STRING_SYSTEM_MESSAGE_READ_SUCCESS ="设置已读状态成功！";
	public static String STRING_SYSTEM_MESSAGE_READ_FAIL ="设置已读状态失败！";
	
	//圈子
	public static Integer STRING_FEELING_PAGESIZE = 10;  //圈子列表每页加载数量
	public static String STRING_FEELING_NO_DATA = "已加载全部数据!";
	public static String STRING_FEELING_ADD_SUCCESS = "圈子发布成功!";
	public static String STRING_FEELING_PRISE_ADD_SUCCESS = "点赞成功!";
	public static String STRING_FEELING_PRISE_EREASE_SUCCESS = "取消点赞成功!";
	public static String STRING_FEELING_REPLY_SUCCESS = "回复成功!";
	public static String STRING_FEELING_COMMENT_SUCCESS = "评论成功!";
	public static String  STRING_USER_FEELING_PICTUREA_FORMAT = "jinzht_feeling_%d%d";
	public static String STRING_FEELING_SHARE_UPDATE = "分享信息更新成功！";
	
	//分享
	public static String STRING_SHARE_APP_URL ="http://a.app.qq.com/o/simple.jsp?pkgname=com.jinzht.pro";
	
	//活动
	public static String STRING_ACTION_ADD_SUCCESS = "报名信息提交成功!";
	public static String STRING_ACTION_ADD_REPEAT = "报名信息已提交，无须重复报名!";
	
	//邀请码
	public static String STRING_SYSTEM_CODE_GENERATE_FORMAT ="JZT%s%d%s";
	public static int INTEGER_SYSTEM_CODE_GENERATE_MAX_VALUE = 6;
	
	//项目
	public static String STRING_PROJECT_NO_DETAIL = "该项目无具体信息";
	public static String STRING_PROJECT_COLLECT_ADD = "关注成功";
	public static String STRING_PROJECT_COLLECT_CANCEL = "取消关注";
	public static String STRING_PROJECT_COMMENT_SUCCESS = "评论成功!";
	public static String STRING_PROJECT_SCENE_SUCCESS = "项目现场获取成功!";
	public static String STRING_PROJECT_SCENE_ADD_SUCCESS = "发送成功!";
	public static String STRING_PROJECT_INVEST_ADD_SUCCESS = "投资信息提交成功!";
	
	
	//易宝支付
	public static String STRING_YEEPAY_VERIFY_ADDRESS = "http://127.0.0.1:8088/";
	public static String STRING_YEEPAY_ENCRYPT_SUCCESS ="加密成功!";
	public static String STRING_YEEPAY_ENCRYPT_FAIL ="加密失败!";
	public static String STRING_YEEPAY_VERIFY_SUCCESS ="校验成功!";
	public static String STRING_YEEPAY_VERIFY_FAIL ="校验失败!";
	
	//投资人
	public static int STRING_INVESTOR_LIST_MAX_SIZE = 10;
	public static int STRING_FOUNDATION_LIST_MAX_SIZE = 3;
	public static String STRING_INVESTOR_PROJECT_COMMIT_SUCCESS ="项目提交成功！投资人处理后第一时间联系您！";
	
	//交易
	public static String STRING_TRADE__ADD_SUCCESS="信息提交成功";
	public static String STRING_TRADE__ADD_FAIl="信息提交失败";
	
	//邮件系统
	public static String STRING_MAIL_SERVER_NAME = "smtp.qq.com";
	public static String STRING_MAIL_SERVER_USER = "chenshengzhu@jinzht.com";
	public static String STRING_MAIL_SERVER_PASSWD = "Csz1126@0314";
	
	//友盟推送
	public static String STRING_JAK = "cc3fdb255d49497c5fd3d402"; // access key
	public static String STRING_JMS = "4bcf1021c013e29b4f77a7b7"; //master secret
	public static String STRING_PUSH_ALERT = "alert";	
	public static String STRING_PUSH_TITLE = "極光推送";	
	public static String STRING_PUSH_CONTENT = "極光推送";	

}
