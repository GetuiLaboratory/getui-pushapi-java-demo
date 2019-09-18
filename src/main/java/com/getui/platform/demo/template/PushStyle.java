package com.getui.platform.demo.template;

import com.gexin.rp.sdk.template.style.*;

/**
 * 推送样式
 *
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
public class PushStyle {

    public static void main(String[] args) {
        getStyle0();
//        getStyle6();
    }

    /**
     * Style0 系统样式
     * @link http://docs.getui.com/getui/server/java/template/ 查看效果
     * @return
     */
    public static AbstractNotifyStyle getStyle0() {
        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png"); //配置通知栏图标，需要在客户端开发时嵌入，默认为push.png
        // 配置通知栏网络图标
        style.setLogoUrl("");

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("通知渠道id");
        style.setChannelName("通知渠道名称");
        style.setChannelLevel(3); //设置通知渠道重要性
        return style;
    }

    /**
     * Style6 展开式通知样式
     * @link http://docs.getui.com/getui/server/java/template/ 查看效果
     * @return
     */
    public static AbstractNotifyStyle getStyle6() {
        Style6 style = new Style6();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png"); //配置通知栏图标，需要在客户端开发时嵌入
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 三种方式选一种
        style.setBigStyle1("bigImageUrl"); //设置大图+文本样式
//        style.setBigStyle2("bigText"); //设置长文本+文本样式

        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("通知渠道id");
        style.setChannelName("通知渠道名称");
        style.setChannelLevel(3); //设置通知渠道重要性
        return style;
    }
}
