package com.cxgitbot.utils;

import net.mamoe.mirai.message.data.MessageChain;

import java.util.Set;

public interface IConfigurable {
    enum Status{
        Nothing,
        WaitingForKey,
        WaitingForValue,
        Configured
    }
    Status CurrentStatus();

    /**
     * 正常情况下返回等待键值
     * @return WaitingForKey
     */
    Status Start();
    void Configure(String key);
    void Configure(MessageChain value);
    Status Next();
    void Reset();
    void Finish();
    Set<String> Keys();
    MessageChain Trigger(MessageChain input);

    String StatusToString();
}
