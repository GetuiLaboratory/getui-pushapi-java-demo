package com.getui.platform.demo.statistics;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;

import java.util.ArrayList;
import java.util.List;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 推送结果相关demo
 *
 * @author zhangwf
 * @see
 * @since 2019-07-11
 */
public class PushResultDemo {


    public static void main(String[] args) {
        getPushResultByTaskId("OSA_xxxx");
        getPushResultByTaskidList();
        getPushResultByGroupName("别名");
        queryAppPushDataByDate("20190710");
    }

    /**
     * 获取推送结果,可查询消息有效可下发总数，消息回执总数，用户点击数等结果。
     * @param taskId
     */
    private static void getPushResultByTaskId(String taskId) {
        IPushResult ret = push.getPushResult(taskId);
        System.out.println(ret.getResponse());
    }

    /**
     * 批量查询推送数据，可查询消息有效可下发总数，消息回执总数，用户点击数结果。
     */
    private static void getPushResultByTaskidList() {
        List<String> taskIdList = new ArrayList<String>();
        taskIdList.add("OSA_xxxx1");
        taskIdList.add("OSA_xxxx2");
        IPushResult ret = push.getPushResultByTaskidList(taskIdList);
        System.out.println(ret.getResponse());

    }

    /**
     * 获取任务组名推送结果,返回结果包括百日内联网用户数（活跃用户数）、实际下发数、到达数、展示数、点击数。
     * @param groupName
     */
    private static void getPushResultByGroupName(String groupName) {
        IQueryResult ret = push.getPushResultByGroupName(APPID, groupName);
        System.out.println(ret.getResponse());
    }

    /**
     * 获取应用单日的推送数据（推送数据包括：发送总数，在线发送数，接收数，展示数，点击数）（目前只支持查询1天前的数据）
     * @param date eg. 20190710
     */
    private static void queryAppPushDataByDate(String date) {
        IQueryResult ret = push.queryAppPushDataByDate(APPID, date);
        System.out.println(ret.getResponse());
    }

}
