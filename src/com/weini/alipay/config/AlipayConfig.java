package com.weini.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	/***
	 * 支付宝支付静态变量
	 * 
	 */
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088521095228055";
	// 商户的私钥
	public static String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALMLVQDQyca51QGVEBg7ZJicTu5zdDq5i8P8W55srLtOb7A2rOLlv3TmefFdnFp8uFOqTB9jE2yDCwQPqhXjc0Mg4f3LrZoYF3F4epiqD/rzjqJ3g9/677IkOCi+nTEjBAIGK64cb3DNHI7Fl4U9apoJg9f03GP/jUfbYOJtIHyFAgMBAAECgYEAqFzAmxq6kvfOeEVutAqtpuBdTNceddZiunIEyW3R4/R3DFHtsxNuPQQ5qSFo1KIxRuN934nwCvsTEceVHi4bBHE6APDo2Rpyindy5gHLGBVi8zB/qmfrOcZNmiN7VrnWNODrNpv1QlOmNqp6lbHb9h2S+JV5dAee9+/UD3irLyUCQQDsX0b+3X6pzwnn/iBowktGOEqDYYrVUsVqrsjc6LLXs23HVQY4Ce0M3fI7JY1rxdZkVqDQsUIX4cBrpgTpz04jAkEAwelljDbuf+jqlzS2FKjlilKoFCh37zezY4N3NubvTb2xqeqXy2xQSHHHiUXQb9rsrBrssOcJn9fDTymTuvYxNwJATH6VAjv6Fsgq44opQN3H8ISdEKGLhXfQ7bJ8zIj9/7JQj5ajeZU0wto83wlLGBqhRD2Is6D5y7LCS9QheO0e0wJAGVzpx1k4AMr6RCTE3BbrTxhYm6pfZY6njBCZ+zhhfI2fO1ZP3G+iYJdhPfc/uCGuayRqaQkPHTUfeq85f27MKQI/RgI7ZpmAdSnWN8X+iN0SuDos3RHp7VSLdIQcxLWKe/0vtPS9+zPt1IzbFw/gAxliBOdnilg/U2G6B0o9Kufh";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/usr/local/tomcat/apache-tomcat-7.0.57/logs/";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";
	
	public static String show_url="m.alipay.com";
	
	//订单回调链接
	public static String notify_wx_url="notifywxurl.action";
	//阿里支付服务器
	public static String server_url="mobile.securitypay.pay";
	//客户端id
	public static String app_id="2016112903550033";
	//客户端来源
	public static String appenv="system=android^version=1.0.1";
	//业务参数
	public static String payment_type="1";
	//卖家账号
	public static String seller = "akzxcsjstz_company@yeah.net";
	//接口名称
	public static String service_str="service";
	//合作者身份ID
	public static String partner_str="partner";
	//参数编码字符集
	public static String _input_charset_str="charset";
	//签名方式
	public static String sign_type_str="sign_type";
	//签名
	public static String sign_str="sign";
	//服务器异步通知页面路径
	public static String notify_url_str="notify_url";
	//客户端号
	public static String app_id_str="app_id";
	//客户端来源 
	public static String app_nev_str="appenv";
	//业务参数
	public static String out_trade_no_str="out_trade_no";
	//商品名称 
	public static String subject_str="subject";
	//支付类型
	public static String payment_type_str="payment_type";
	//卖家支付宝账号
	public static String seller_id_str="seller_id";
	//总金额
	public static String total_fee_str="total_amount";
	//商品详情
	public static String body_str="body";
	//未付款交易超时时间
	public static String it_b_pay_str="it_b_pay";
	//授权令牌
	public static String extern_token_str="extern_token";
	//商品名称
	public static String subject="城投逸园缴费";
	//默认商品描述
	public static String body="一键服务缴费";
	
	public static String show_url_str="show_url";
	
	/***
	 * 微信支付静态变量
	 * 
	 */
	//对应微信开放平台应用AppId
	public static String APP_ID="wx33aa0167f6a81dac";  //APPID
	public static String APP_ID_SERVER="wxdcd3847ae7077ffe";  //APPID 
	public static String APP_SECRET="4ab074a137e5cc81df67808c1f23b8aa"; //APPSECRET
	//商户号，填写商户对应参数
	public static String MCH_ID="1276917501";
	public static String MCH_ID_SERVER="1426516702";
	//商户API密钥，填写相应参数
	public static String PARTNER_ID="wuxzaihsxadminwuxzaihsxadminwuxz";
	//支付结果回调页面
	public static String NOTIFY_URL="notifywxurl.action";
	public static String API_CHECK="https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	//字段
	public static String APP_ID_STR="appid";  //开放平台app_id
	public static String MCH_ID_STR="mch_id"; //商户号
	public static String NONCE_STR="nonce_str"; //随即字符串
	public static String TRADE_TYPE_STR="trade_type"; //支付类型
	public static String TRADE_TYPE_JSAPI="JSAPI"; //支付类型
	public static String BODY_STR="body"; //订单描述，展示给用户
	public static String NOTIFY_URL_STR="notify_url"; //异步通知
	public static String OUT_TRADE_NO_STR="out_trade_no";//商户订单号
	public static String SPBILL_CREATE_IP_STR="spbill_create_ip"; //发起订单支付机器ip
	public static String TOTAL_FEE="total_fee"; //支付总金额
	public static String SERVER_IP="59.110.11.95";  //服务器ip
	
	
	
	
	
	
	

}
