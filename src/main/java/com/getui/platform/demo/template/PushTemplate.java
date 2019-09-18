package com.getui.platform.demo.template;

import com.gexin.fastjson.JSONObject;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.base.payload.VoIPPayload;
import com.gexin.rp.sdk.template.*;

import static com.getui.platform.demo.constant.AppInfo.APPID;
import static com.getui.platform.demo.constant.AppInfo.APPKEY;

/**
 * 推送模板
 *
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
public class PushTemplate {

    public static void main(String[] args) {
        getNotificationTemplate();
//        getLinkTemplate();
//        getTransmissionTemplate();
//        getStartActivityTemplate();
//        getRevokeTemplate("XXXX");
    }

    /**
     * 点击通知打开应用模板, 在通知栏显示一条含图标、标题等的通知，用户点击后激活您的应用。
     * 通知模板(点击后续行为: 支持打开应用、发送透传内容、打开应用同时接收到透传 这三种行为)
     * @return
     */
    public static NotificationTemplate getNotificationTemplate() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(APPID);
        template.setAppkey(APPKEY);
        //设置展示样式
        template.setStyle(PushStyle.getStyle0());
        template.setTransmissionType(1);  // 透传消息设置，收到消息是否立即启动应用： 1为立即启动，2则广播等待客户端自启动
        template.setTransmissionContent("请输入您要透传的内容");
//        template.setSmsInfo(PushSmsInfo.getSmsInfo()); //短信补量推送

//        template.setDuration("2019-07-09 11:40:00", "2019-07-09 12:24:00");  // 设置定时展示时间，安卓机型可用
        template.setNotifyid(123); // 在消息推送的时候设置notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。
        return template;
    }

    /**
     * 点击通知打开(第三方)网页模板, 在通知栏显示一条含图标、标题等的通知，用户点击可打开您指定的网页。
     * @return
     */
    public static LinkTemplate getLinkTemplate() {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(APPID);
        template.setAppkey(APPKEY);

        //设置展示样式
        template.setStyle(PushStyle.getStyle0());
        template.setUrl("http://www.baidu.com");  // 设置打开的网址地址
        template.setNotifyid(123); // 在消息推送的时候设置notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。
//         template.setSmsInfo(PushSmsInfo.getSmsInfo()); //短信补量推送
//        template.setDuration("2019-07-09 11:40:00", "2019-07-09 12:24:00");  // 设置定时展示时间，安卓机型可用
        return template;
    }

    /**
     * 透传消息模版,透传消息是指消息传递到客户端只有消息内容，展现形式由客户端自行定义。客户端可自定义通知的展现形式，也可自定义通知到达之后的动作，或者不做任何展现。
     * @return
     */
    public static TransmissionTemplate getTransmissionTemplate() {
        TransmissionTemplate template = new TransmissionTemplate();
        // 设置APPID与APPKEY
        template.setAppId(APPID);
        template.setAppkey(APPKEY);

        //透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent("透传内容"); //透传内容
        template.setAPNInfo(getAPNPayload()); //ios消息推送
//        template.setAPNInfo(getVoIPPayload());
//        template.setSmsInfo(PushSmsInfo.getSmsInfo()); //短信补量推送
        return template;
    }

    //点击通知, 打开（自身）应用内任意页面
    public static StartActivityTemplate getStartActivityTemplate() {
        StartActivityTemplate template = new StartActivityTemplate();
        // 设置APPID与APPKEY
        template.setAppId(APPID);
        template.setAppkey(APPKEY);
        //设置展示样式
        template.setStyle(PushStyle.getStyle0());

        String intent = "intent:#Intent;component=com.yourpackage/.NewsActivity;end";
        template.setIntent(intent); //最大长度限制为1000
        template.setNotifyid(123); // 在消息推送的时候设置notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。
//        template.setSmsInfo(PushSmsInfo.getSmsInfo()); //短信补量推送
//        template.setDuration("2019-07-09 11:40:00", "2019-07-09 12:24:00");  // 设置定时展示时间，安卓机型可用
        return template;
    }

    /**
     * 消息撤回模版
     * @param taskId
     * @return
     */
    public static RevokeTemplate getRevokeTemplate(String taskId) {
        RevokeTemplate template = new RevokeTemplate();
        // 设置APPID与APPKEY
        template.setAppId(APPID);
        template.setAppkey(APPKEY);
        template.setOldTaskId(taskId); //指定需要撤回消息对应的taskId
        template.setForce(false); // 客户端没有找到对应的taskid，是否把对应appid下所有的通知都撤回

        return template;
    }

    private static APNPayload getAPNPayload() {
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        //ios 12.0 以上可以使用 Dictionary 类型的 sound
        payload.setSound("default");
        payload.setCategory("$由客户端定义");
        payload.addCustomMsg("由客户自定义消息key", "由客户自定义消息value");

        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
//        payload.setAlertMsg(getDictionaryAlertMsg());  //字典模式使用APNPayload.DictionaryAlertMsg

        //设置语音播报类型，int类型，0.不可用 1.播放body 2.播放自定义文本
        payload.setVoicePlayType(2);
        //设置语音播报内容，String类型，非必须参数，用户自定义播放内容，仅在voicePlayMessage=2时生效
        //注：当"定义类型"=2, "定义内容"为空时则忽略不播放
        payload.setVoicePlayMessage("定义内容");

        // 添加多媒体资源
        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.pic)
                .setResUrl("资源文件地址")
                .setOnlyWifi(true));

        return payload;
    }

    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg() {
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody("body1");
        alertMsg.setActionLocKey("显示关闭和查看两个按钮的消息");
        alertMsg.setLocKey("loc-key1");
        alertMsg.addLocArg("loc-ary1");
        alertMsg.setLaunchImage("调用已经在应用程序中绑定的图形文件名");
        // iOS8.2以上版本支持
        alertMsg.setTitle("通知标题");
        alertMsg.setTitleLocKey("自定义通知标题");
        alertMsg.addTitleLocArg("自定义通知标题组");
        return alertMsg;
    }

    /**
     * 需要使用iOS语音传输，请使用VoIPPayload代替APNPayload
     * @return
     */
    private static VoIPPayload getVoIPPayload() {
        VoIPPayload payload = new VoIPPayload();
        JSONObject jo = new JSONObject();
        jo.put("key1", "value1");
        payload.setVoIPPayload(jo.toString());
        return payload;
    }
}
