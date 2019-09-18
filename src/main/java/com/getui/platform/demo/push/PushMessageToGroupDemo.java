package com.getui.platform.demo.push;

import com.getui.platform.demo.template.PushTemplate;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.GroupMessage;

import java.util.ArrayList;
import java.util.List;

import static com.getui.platform.demo.constant.AppInfo.*;

/**
 * 推送消息到指定直播间相关demo
 * 此接口需要申请开通
 * @author zhangwf
 * @see
 * @since 2019-08-05
 */
public class PushMessageToGroupDemo {

    /**
     * 直播间接口是针对直播间类的应用使用个推sdk推送直播间内消息给直播间成员所做的专用接口。原本的tolist接口推送对大量频繁的聊天消息存在效率低、大型直播间需要调多次传cid等问题，而新增的直播间接口对这些都进行了优化。
     * @param args
     */
    public static void main(String[] args) {
        String groupId = createGroup("直播间001");
        updateGroup(groupId);
        pushMessageToGroup(groupId, "请求ID");
//        removeGroup(groupId);
        queryCidStatusByGroupId(groupId);
        queryCidsByGroupId(groupId);
    }

    /**
     * 创建房间
     *
     * @param groupName
     * @return
     */
    private static String createGroup(String groupName) {
        IPushResult ret = push.createGroup(APPID, groupName);
        System.out.println(ret.getResponse());
        return ret.getResponse().get("groupId").toString();
    }

    /**
     * 更新房间的cid用户列表
     *
     * @param groupId
     */
    private static void updateGroup(String groupId) {
        //需要加入房间的cid列表，可为null和空列表
        List<String> addCids = new ArrayList<String>();
        addCids.add(CID);
        addCids.add(CID_2);
        //需要删除房间的cid列表，可为null和空列表
        List<String> delCids = new ArrayList<String>();
        delCids.add("cid3");
        delCids.add("cid4");
        IPushResult ret = push.updateGroup(APPID, groupId, addCids, delCids);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 对房间推送消息
     *
     * @param groupId   房间ID
     * @param requestId 保证每条消息唯一性，防止相同消息重复下发
     */
    private static void pushMessageToGroup(String groupId, String requestId) {
        GroupMessage message = new GroupMessage();
        message.setData(PushTemplate.getNotificationTemplate());
        message.setGroupId(groupId);
        message.setRequestId(requestId);

        IPushResult ret = push.pushMessageToGroup(APPID, message);
        System.out.println(ret.getResponse().toString());
    }

    /**
     * 销毁房间
     * 当直播结束后进行销毁房间操作,如不进行销毁房间操作，该房间将默认在最后一次更新cid后继续存在一天，一天后自动删除
     *
     * @param groupId
     */
    private static void removeGroup(String groupId) {
        IPushResult ret = push.removeGroup(APPID, groupId);
        System.out.println(ret.getResponse());
    }

    /**
     * 查询cid状态
     *
     * @param groupId
     */
    private static void queryCidStatusByGroupId(String groupId) {
        List<String> cidList = new ArrayList<String>();
        cidList.add(CID);
        IPushResult ret = push.queryCidStatusByGroupid(APPID, groupId, cidList);
        System.out.println(ret.getResponse());
    }


    /**
     * 查询cid列表
     *
     * @param groupId
     */
    private static void queryCidsByGroupId(String groupId) {
        IPushResult ret = push.queryCidsByGroupid(APPID, groupId);
        System.out.println(ret.getResponse());
    }

}
