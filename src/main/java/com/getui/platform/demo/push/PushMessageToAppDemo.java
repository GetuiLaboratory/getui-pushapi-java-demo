package com.getui.platform.demo.push;


import com.getui.platform.demo.template.PushTemplate;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.PushAppException;
import com.gexin.rp.sdk.template.LinkTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 对指定应用群推消息相关demo
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
public class PushMessageToAppDemo {

    public static void main(String[] args) {
        String taskId = pushMessageToApp();
//        getScheduleTask(taskId);
//        delScheduleTask(taskId);
//        stopTask(taskId);
    }

    /**
     * 对指定应用群推消息
     *
     * 场景1，某app周年庆，群发消息给该app的所有用户，提醒用户参加周年庆活动。
     * 场景2，按城市接口推送
     * 场景3，按按照tag推送
     * @return
     */
    private static String pushMessageToApp() {
        LinkTemplate template = PushTemplate.getLinkTemplate();
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setOffline(true);
        message.setOfflineExpireTime(24 * 1000 * 3600);  //离线有效时间，单位为毫秒，可选
        // APNs下发策略；1: 个推通道优先，在线经个推通道下发，离线经APNs下发(默认);2: 在离线只经APNs下发;3: 在离线只经个推通道下发;4: 优先经APNs下发，失败后经个推通道下发;
        message.setStrategyJson("{\"ios\":4}");
        //全量推送时希望能控制推送速度不要太快，缓减服务器连接压力，可设置定速推送。如果未设置则按默认推送速度发送
//        message.setSpeed(100); // 设置为100，含义为个推控制下发速度在100条/秒左右

        //设置推送时间，需要申请开通套餐
//        try {
//            message.setPushTime("201907121810"); //2019年07月12日18:10分开始推送，限制条件参见官网（http://docs.getui.com/getui/server/java/push/）定时推送有关说明
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        List<String> appIdList = new ArrayList<String>();
        appIdList.add(APPID);
        message.setAppIdList(appIdList);

        //推送给App的目标用户需要满足的条件
        AppConditions cdt = new AppConditions();

        //手机类型
        List<String> phoneTypeList = new ArrayList<String>();
        phoneTypeList.add("IOS");
        phoneTypeList.add("ANDROID");

        //地区
        List<String> regionList = new ArrayList<String>();
        //参见 datafile目录下 region_code.data
        regionList.add("33010000");//杭州市
        regionList.add("51010000");//成都市

        //自定义tag
        List<String> tagList = new ArrayList<String>();
        tagList.add(TAG);
        tagList.add(TAG_2);

        //查询可推送的用户画像（需要开通VIP套餐)
        IQueryResult personaTagResult = push.getPersonaTags(APPID);
        System.out.println(personaTagResult.getResponse());

        //工作
        List<String> jobs = new ArrayList<String>();
        jobs.add("0102");
        jobs.add("0110");

        //年龄
        List<String> age = new ArrayList<String>();
        age.add("0000");

        //条件交并补功能, 默认是与
        cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList, AppConditions.OptType.or);
        cdt.addCondition(AppConditions.REGION, regionList, AppConditions.OptType.or);
        cdt.addCondition(AppConditions.TAG, tagList, AppConditions.OptType.not);
        cdt.addCondition("job", jobs);
        cdt.addCondition("age", age);
        message.setConditions(cdt);

        IPushResult ret = null;
        try {
            ret = push.pushMessageToApp(message);
//            ret = push.pushMessageToApp(message, "任务别名_toApp");
        } catch (PushAppException e) {
            e.printStackTrace();
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
            return ret.getResponse().get("contentId").toString();
        } else {
            System.out.println("服务器响应异常");
            return null;
        }
    }

    /**
     * 定时任务查询接口
     * @param taskId
     */
    private static void getScheduleTask(String taskId) {
        IPushResult ret = push.getScheduleTask(taskId, APPID);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 定时任务删除接口
     * @param taskId
     */
    private static void delScheduleTask(String taskId) {
        IPushResult ret = push.delScheduleTask(taskId, APPID);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 对正处于推送状态，或者未接收的消息停止下发
     * @param taskId
     */
    private static void stopTask(String taskId) {
        boolean ret = push.stop(taskId);
        System.out.println(ret);
    }

}
