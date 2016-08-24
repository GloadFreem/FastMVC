package com.jinzht.tools;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushUtil {
	
	public static void main(String[] args)
	{
		new PushUtil().send();
	}

	public void send() {
		JPushClient jpushClient = new JPushClient(Config.STRING_JMS, Config.STRING_JAK, 3);

		// For push, all you need do is to build PushPayload object.
//		PushPayload payload = buildPushObject_all_all_alert();
		PushPayload payload = buildPushObject_ios_tagAnd_alertWithExtrasAndMessage();
//		PushPayload payload = buildPushObject_android_tag_alertWithTitle();

		try {
			PushResult result = jpushClient.sendPush(payload);
//			LOG.info("Got result - " + result);
			System.out.println("Got result - " + result);

		} catch (APIConnectionException e) {
			// Connection error, should retry later
//			LOG.error("Connection error, should retry later", e);
			System.out.println("Connection error, should retry later" + e);

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
//			LOG.error("Should review the error, and fix the request", e);
//			LOG.info("HTTP Status: " + e.getStatus());
//			LOG.info("Error Code: " + e.getErrorCode());
//			LOG.info("Error Message: " + e.getErrorMessage());
			
			System.out.println("Should review the error, and fix the request" + e);
			System.out.println("HTTP Status: " + e);
			System.out.println("Connection error, should retry later" + e.getStatus());
			System.out.println("Error Code:" + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());
			
		}
	}
	
	public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload
        		.alertAll(Config.STRING_PUSH_ALERT);
    }
	
	public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(Config.STRING_PUSH_ALERT))
                .build();
    }
	
	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
//                .setAudience(Audience.tag("tag1"))
                .setAudience(Audience.registrationId("010414de51a"))
//                .setAudience(Audience.registrationId("0a054121c82"))
                .setNotification(Notification.android(Config.STRING_PUSH_ALERT, Config.STRING_PUSH_ALERT, null))
//                .setMessage(Message.newBuilder()
//                        .setMsgContent(Config.STRING_PUSH_CONTENT)
//                         .addExtra("type", "project")
//                         .addExtra("url", "http://www.jinzht.com")
//                        .build())
                 .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(Config.STRING_PUSH_ALERT)
                                .addExtra("type", "authentic")
                                .addExtra("content", "认证成功")
                                .addExtra("ext", "成功")
                                .build())
                        .build())
                .build();
    }
	
	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
//                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setAudience(Audience.registrationId("191e35f7e043cfe8a4c"))
//                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(Config.STRING_PUSH_ALERT)
                                .setBadge(1)
                                .setSound("happy.caf")
                                .addExtra("type", "action")
                                .addExtra("content", "1")
                                .addExtra("ext", "活动")
                                .build())
                        .build())
                 .setMessage(Message.content(Config.STRING_PUSH_CONTENT))
                 .setOptions(Options.newBuilder()
//                         .setApnsProduction(true)
                         .setApnsProduction(false)
                         .build())
                 .build();
    }
	
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(Config.STRING_PUSH_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

}
