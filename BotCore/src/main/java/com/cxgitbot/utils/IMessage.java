package com.cxgitbot.utils;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;

/**
 * 消息接口
 */
public interface IMessage {

    /**
     * Bot接收到的来自群组的消息
     * @param message 消息
     */
    void Receive(GroupMessageEvent message);

    void Nudge(NudgeEvent nudge);
}
