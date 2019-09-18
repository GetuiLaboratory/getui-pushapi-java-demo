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
}
