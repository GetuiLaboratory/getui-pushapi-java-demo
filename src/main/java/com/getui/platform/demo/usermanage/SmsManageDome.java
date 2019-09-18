package com.getui.platform.demo.usermanage;

import com.gexin.rp.sdk.base.IPushResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 短信管理相关demo
 * 进行短信补量推送前，必须在个推申请开通短信补量并报备短信模板。短信模板审核通过后，才能进行短信补量推送.
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
public class SmsManageDome {

    public static void main(String[] args) {
        bindPn(CID, PNMD5);
//        queryCidPn(CID);
//        unBindPn(CID);
    }

    /**
     * cid与pn绑定接口
     * pnMd5 是指md5之后的手机号, 32位小写
     * @param cid
     * @param pnMd5
     */
    private static void bindPn(String cid, String pnMd5) {
        Map<String, String> cidAndPn = new HashMap<String, String>();
        cidAndPn.put(cid, pnMd5); //最多支持一次绑定50个cid与pn的对应关系
        IPushResult ret = push.bindCidPn(APPID, cidAndPn);
        System.out.println(ret.getResponse());
    }

    /**
     * pn查询接口
     * @param cid
     */
    private static void queryCidPn(String cid) {
        List<String> cidList = new ArrayList<String>();
        cidList.add(cid);
        IPushResult ret = push.queryCidPn(APPID, cidList);
        System.out.println(ret.getResponse());
    }

    /**
     * cid与pn解绑接口
     * @param cid
     */
    private static void unBindPn(String cid) {
        List<String> cidList = new ArrayList<String>();
        cidList.add(cid); //最多支持一次解绑50个cid
        IPushResult ret = push.unbindCidPn(APPID, cidList);
        System.out.println(ret.getResponse());
    }

}
