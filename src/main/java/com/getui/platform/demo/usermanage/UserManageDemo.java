package com.getui.platform.demo.usermanage;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;

import java.util.ArrayList;
import java.util.List;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 用户管理相关demo
 *
 * @author zhangwf
 * @see
 * @since 2019-07-11
 */
public class UserManageDemo {

    public static void main(String[] args) {
        getClientIdStatus();

//        addCidListToBlk();
//        restoreCidListFromBlk();
    }

    /**
     * 获取用户状态
     */
    private static void getClientIdStatus() {
        IQueryResult ret = push.getClientIdStatus(APPID, CID);
        System.out.println(ret.getResponse());
    }

    /**
     * 添加黑名单用户
     */
    private static void addCidListToBlk() {
        List<String> cidList = new ArrayList<String>();
        cidList.add(CID);
        IPushResult ret = push.addCidListToBlk(APPID, cidList);
        System.out.println("黑名单增加结果：" + ret.getResponse());
    }

    /**
     * 移除黑名单用户
     */
    private static void restoreCidListFromBlk() {
        List<String> cidList = new ArrayList<String>();
        cidList.add(CID);
        IPushResult ret = push.restoreCidListFromBlk(APPID, cidList);
        System.out.println("黑名单删除结果：" + ret.getResponse());
    }
}
