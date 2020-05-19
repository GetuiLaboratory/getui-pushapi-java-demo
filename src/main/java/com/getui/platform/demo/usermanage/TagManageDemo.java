package com.getui.platform.demo.usermanage;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;

import java.util.ArrayList;
import java.util.List;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * tag管理相关demo
 *
 * @author zhangwf
 * @see
 * @since 2019-07-11
 */
public class TagManageDemo {

    public static void main(String[] args) {
        setClientTag();
        getUserTags();
        getUserListTags();
    }

    /**
     * 对指定用户设置tag属性
     */
    private static void setClientTag () {
        List<String> tagList = new ArrayList<String>();
        tagList.add("18-20");
        tagList.add("杭州");
        tagList.add("美女");
        IQueryResult ret = push.setClientTag(APPID, CID, tagList);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 获取指定用户的tag属性
     */
    private static void getUserTags () {
        IPushResult ret = push.getUserTags(APPID, CID);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 批量获取用户的tag属性
     */
    public static void getUserListTags() {
        List<String> list = new ArrayList<String>(2);
        list.add(CID);
        list.add(CID_2);
        IPushResult ret = push.getUserListTags(APPID, list);
        System.out.println(ret.getResponse().toString());
    }
}
