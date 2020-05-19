package com.getui.platform.demo.push;

import com.getui.platform.demo.template.PushTemplate;
import com.gexin.rp.sdk.base.IBatch;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.Constants;
import com.gexin.rp.sdk.template.AbstractTemplate;

import java.io.IOException;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 单推和批量单推相关demo
 *
 * @author zhangwf
 * @see
 * @since 2019-07-09
 */
public class PushMessageToSingleDemo {

    public static void main(String[] args) {
        System.setProperty("needOSAsigned", "true");
        // 返回别名对应的每个cid的推送详情
        System.setProperty(Constants.GEXIN_PUSH_SINGLE_ALIAS_DETAIL, "true");
        pushToSingle();
//        pushToSingleBatch();
    }

    /**
     * 对单个用户推送消息
     * <p>
     * 场景1：某用户发生了一笔交易，银行及时下发一条推送消息给该用户。
     * <p>
     * 场景2：用户定制了某本书的预订更新，当本书有更新时，需要向该用户及时下发一条更新提醒信息。
     * 这些需要向指定某个用户推送消息的场景，即需要使用对单个用户推送消息的接口。
     */
    private static void pushToSingle() {
        AbstractTemplate template = PushTemplate.getTransmissionTemplate(); //通知模板(点击后续行为: 支持打开应用、发送透传内容、打开应用同时接收到透传 这三种行为)
//        AbstractTemplate template = PushTemplate.getLinkTemplate(); //点击通知打开(第三方)网页模板
//        AbstractTemplate template = PushTemplate.getTransmissionTemplate(); //透传消息模版
//        AbstractTemplate template = PushTemplate.getRevokeTemplate(); //消息撤回模版
//        AbstractTemplate template = PushTemplate.getStartActivityTemplate(); //点击通知, 打开（自身）应用内任意页面

        // 单推消息类型
        SingleMessage message = getSingleMessage(template);
        Target target = new Target();
        target.setAppId(APPID);
        target.setClientId(CID);
//        target.setAlias(ALIAS); //别名需要提前绑定

        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    /**
     * 批量单推
     * <p>
     * 当单推任务较多时，推荐使用该接口，可以减少与服务端的交互次数。
     */
    private static void pushToSingleBatch() {
        IBatch batch = push.getBatch();

        IPushResult ret = null;
        try {
            //构建客户a的透传消息a
            constructClientTransMsg(CID, batch);
            //构建客户B的点击通知打开网页消息b
            constructClientLinkMsg(CID_2, batch);
            ret = batch.submit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ret = batch.retry();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }

    private static void constructClientTransMsg(String cid, IBatch batch) throws Exception {
        AbstractTemplate template = PushTemplate.getTransmissionTemplate();
        SingleMessage message = getSingleMessage(template);

        // 设置推送目标，填入appid和clientId
        Target target = new Target();
        target.setAppId(APPID);
        target.setClientId(cid);
        batch.add(message, target);
    }

    private static void constructClientLinkMsg(String cid, IBatch batch) throws Exception {
        AbstractTemplate template = PushTemplate.getLinkTemplate();
        SingleMessage message = getSingleMessage(template);

        // 设置推送目标，填入appid和clientId
        Target target = new Target();
        target.setAppId(APPID);
        target.setClientId(cid);
        batch.add(message, target);
    }

    private static SingleMessage getSingleMessage(AbstractTemplate template) {
        SingleMessage message = new SingleMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(72 * 3600 * 1000);
        // 判断客户端是否wifi环境下推送。1为仅在wifi环境下推送，0为不限制网络环境，默认不限
        message.setPushNetWorkType(1);
        message.setPushNetWorkType(1); // 判断客户端是否wifi环境下推送。1为仅在wifi环境下推送，0为不限制网络环境，默认不限
        // 厂商下发策略；1: 个推通道优先，在线经个推通道下发，离线经厂商下发(默认);2: 在离线只经厂商下发;3: 在离线只经个推通道下发;4: 优先经厂商下发，失败后经个推通道下发;
        message.setStrategyJson("{\"default\":4,\"ios\":4,\"st\":4}");
        return message;
    }

}
