package com.getui.platform.demo.usermanage;

import com.gexin.rp.sdk.base.IQueryResult;

import java.util.ArrayList;
import java.util.List;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * IOS角标设置管理相关demo
 *
 * @author zhangwf
 * @see
 * @since 2019-07-12
 */
public class BadgeManageDemo {

    public static void main(String[] args) {
        setBadgeForCid();
        setBadgeForDeviceToken();
    }

    /**
     * cid角标设置
     */
    public static void setBadgeForCid() {
        List<String> cidList = new ArrayList<String>();
        cidList.add(CID);

        //用户应用icon上显示的数字
        String badge = "+1";   //"+1"即在原有badge上加1；具体详情使用请参考该接口描述
//        String badge = "-1";   //"-1"即在原有badge上减1；具体详情使用请参考该接口描述
//        String badge = "1";    //直接设置badge数值，会覆盖原有数值；具体详情使用请参考该接口描述
        IQueryResult res = push.setBadgeForCID(badge, APPID, cidList);
        System.out.println(res);
    }

    /**
     * deviceToken角标设置
     */
    public static void setBadgeForDeviceToken() {
        List<String> targetList = new ArrayList<String>();
        targetList.add(DEVICETOKEN);

        //用户应用icon上显示的数字
        String badge = "+1"; //"+1"即在原有badge上加1；具体详情使用请参考该接口描述
//        String badge = "-1"; //"-1"即在原有badge上减1；具体详情使用请参考该接口描述
//        String badge = "1"; //直接设置badge数值，会覆盖原有数值；具体详情使用请参考该接口描述
        IQueryResult res = push.setBadgeForDeviceToken(badge, APPID, targetList);
        System.out.println(res);
    }

}
