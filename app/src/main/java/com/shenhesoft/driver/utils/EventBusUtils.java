package com.shenhesoft.driver.utils;

import com.shenhesoft.driver.bean.Event;

import org.greenrobot.eventbus.EventBus;

/**
 * @author 马山水
 * @date 2018/3/15
 * @desc TODO
 */

public class EventBusUtils {
    public static void register(Object subscriber) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        if(EventBus.getDefault().isRegistered(subscriber)){
            EventBus.getDefault().unregister(subscriber);
        }
    }

    public static void sendEvent(Event event) {
        EventBus.getDefault().post(event);
    }

    public static void sendStickyEvent(Event event) {
        EventBus.getDefault().postSticky(event);
    }

    /**
     * 通过code码区分事件类型
     */
    public static final class EventCode {
        //收到推送消息后的事件
        public static final int A = 0x111111;
        //上传到货信息完成后的事件
        public static final int B = 0x222222;
        public static final int C = 0x333333;
        public static final int D = 0x444444;
        public static final int E = 0x555555;
        // other more
    }
}
