package com.getui.platform.demo.template;

import com.gexin.rp.sdk.base.sms.SmsInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信推送
 *
 * @author zhangwf
 * @see
 * @since 2019-07-11
 */
public class PushSmsInfo {

    /**
     * 短信模板和APPLink只能选其一
     * @return
     */
    public static SmsInfo getSmsInfo() {
        SmsInfo smsinfo = new SmsInfo();
        //短信模板ID 需要在个推报备开通 才可使用
        smsinfo.setSmsTemplateId("2017011");
        //模板中占位符的内容k.v 结构
        //注意当使用AppLink时，smsContent不能传值url
        Map<String, String> smsContent = new HashMap<String, String>();
        smsContent.put("code", "1234");
        smsContent.put("time", "1234");
        smsinfo.setSmsContent(smsContent);

//        smsinfo.setApplink(true); //推送的短信模板中是否选用APPLink进行推送。
//        smsinfo.setUrl("www.baidu.com"); //推送的短信模板中的APPLink链接地址。
//        smsinfo.setPayload("222"); //推送的短信模板中的APPLink自定义字段。

        //离线多久后开始补发（单位:ms）
        smsinfo.setOfflineSendtime(100000L);
        return smsinfo;
    }
}
