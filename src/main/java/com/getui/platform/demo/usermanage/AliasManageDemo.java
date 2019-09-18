package com.getui.platform.demo.usermanage;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.base.impl.Target;

import java.util.ArrayList;
import java.util.List;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 别名管理相关demo
 *
 * @author zhangwf
 * @see
 * @since 2019-07-11
 */
public class AliasManageDemo {

    public static void main(String[] args) {
        bindSingleAlias();
        bindListAlias();

        queryClientIdsByAlias();
        queryAliasByCClientId();

        unBindAlias();
        unBindAliasAll();
    }

    /**
     * 单个clientid绑定别名, 一个clientid只能绑定一个别名，若已绑定过别名的clientid再次绑定新别名，则认为与前一个别名自动解绑，绑定新别名。
     */
    private static void bindSingleAlias() {
        IAliasResult ret = push.bindAlias(APPID, ALIAS, CID);
        System.out.println("绑定结果：" + ret.getResponse());
    }

    /**
     * 多个clientid绑定别名, 允许将多个clientid和一个别名绑定，如用户使用多终端，则可将多终端对应的clientid绑定为一个别名，目前一个别名最多支持绑定10个clientid。
     */
    private static void bindListAlias() {
        List<Target> Lcids = new ArrayList<Target>();
        Target target1 = new Target();
        Target target2 = new Target();
        target1.setClientId(CID);
        target1.setAlias(ALIAS);
        target2.setClientId(CID_2);
        target2.setAlias(ALIAS_2);
        Lcids.add(target1);
        Lcids.add(target2);

        IAliasResult ret = push.bindAlias(APPID, Lcids);
        System.out.println("绑定结果：" + ret.getResponse());
    }

    /**
     * 根据别名获取clientid信息
     */
    private static void queryClientIdsByAlias() {
        IAliasResult ret = push.queryClientId(APPID, ALIAS);
        System.out.println(ret.getResponse());
        System.out.println("根据别名获取的CID：" + ret.getResponse().get("cidlist"));
    }

    /**
     * 通过clientid获取别名信息
     */
    private static void queryAliasByCClientId() {
        IAliasResult ret = push.queryAlias(APPID, CID);
        System.out.println(ret.getResponse());
        System.out.println("根据CID获取的别名：" + ret.getResponse().get("alias"));
    }

    /**
     * 单个clientid和别名解绑
     */
    private static void unBindAlias() {
        IAliasResult ret = push.unBindAlias(APPID, ALIAS, CID);
        System.out.println("解除绑定结果:" + ret.getResponse());
    }

    /**
     * 绑定别名的所有clientid解绑
     */
    private static void unBindAliasAll() {
        IAliasResult ret = push.unBindAliasAll(APPID, ALIAS);
        System.out.println("解除绑定结果:" + ret.getResponse());
    }
}

