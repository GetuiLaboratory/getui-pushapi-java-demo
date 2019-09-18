package com.getui.platform.demo.statistics;

import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.uitls.AppConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 用户统计相关demo
 *
 * @author zhangwf
 * @see
 * @since 2019-07-24
 */
public class UserStatisticsDemo {
    public static void main(String[] args) {
        getLast24HoursOnlineUserStatistics();
//        queryAppUserDataByDate("20190715");
//
//        getPersonaTags();
//        queryUserCount();
    }

    /**
     * 通过接口查询当前时间一天内的在线数（十分钟一个点，一小时六个点）
     */
    private static void getLast24HoursOnlineUserStatistics () {
        IQueryResult queryResult = push.getLast24HoursOnlineUserStatistics(APPID);
        System.out.println(queryResult.getResponse().get("onlineStatics"));
    }

    /**
     * 获取某个应用单日的用户数据（用户数据包括：新增用户数，累计注册用户总数，在线峰值，日联网用户数）（目前只支持查询1天前的数据）
     * @param date eg. 20190715
     */
    private static void queryAppUserDataByDate(String date) {
        IQueryResult ret = push.queryAppUserDataByDate(APPID, date);
        System.out.println(ret.getResponse().toString());

        Map<String, Object> data = (Map<String, Object>) ret.getResponse().get("data");
        System.out.println("新用户注册总数:"+data.get("newRegistCount"));
        System.out.println("用户注册总数:"+data.get("registTotalCount"));
        System.out.println("活跃用户数:"+data.get("activeCount"));
        System.out.println("在线用户数:"+data.get("onlineCount"));
    }

    /**
     * 查询符合当前查询条件的用户数
     */
    private static void queryUserCount () {
        AppConditions conditions = new AppConditions();
        //机型
        List<String> phoneTypes = new ArrayList<String>();
        phoneTypes.add("ANDROID");
        conditions.addCondition(AppConditions.PHONE_TYPE, phoneTypes);

        //地区
        List<String> regions = new ArrayList<String>();
        //参见 datafile目录下 region_code.data
        regions.add("33000000");
        conditions.addCondition(AppConditions.REGION, regions);

        //tag
        List<String> tags = new ArrayList<String>();
        tags.add("haha");
        conditions.addCondition(AppConditions.TAG, tags);

        //用户画像(可以通过getPersonaTags接口查询)
        //工作
//        List<String> jobs = new ArrayList<String>();
//        jobs.add("0102");
//        jobs.add("0110");
//        conditions.addCondition("job", jobs);

        //年龄
//        List<String> age = new ArrayList<String>();
//        age.add("0000");
//        conditions.addCondition("age", age);

        //查询
        IQueryResult  ret = push.queryUserCount(APPID, conditions);
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    /**
     * 查询当前bi统计的用户画像标签, 该接口需要申请后才可正常使用，且主要是让APP查看自己能使用那些标签进行推送
     */
    private static void getPersonaTags () {
        IQueryResult ret = push.getPersonaTags(APPID);
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
}
