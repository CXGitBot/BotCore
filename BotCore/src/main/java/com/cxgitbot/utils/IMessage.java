package com.cxgitbot.utils;

import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 消息接口
 */
public interface IMessage {

    /**
     * Bot接收到的来自群组的消息
     * @param message 消息
     */
    void Receive(GroupMessageEvent message);
}
