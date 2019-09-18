package com.getui.platform.demo.constant;

import com.gexin.rp.sdk.http.IGtPush;

/**
 * AppInfo 相关信息
 *
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
public class AppInfo {
    public static final String APPID = "";
    public static final String APPKEY = "";
    public static final String MASTERSECRET = "";

    public static final String CID = "";
    public static final String CID_2 = "";

    public static final String DEVICETOKEN = "";

    public static final String ALIAS = "alias1";
    public static final String ALIAS_2 = "alias2";

    public static final String TAG = "tag1";
    public static final String TAG_2 = "tag2";

    public static final String PNMD5 = "xxxx";

    public static IGtPush push = new IGtPush(APPKEY, MASTERSECRET);
}
