package com.cxgitbot.utils;

import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;

public interface IReply {
    enum Tension{
        Positive,
        Negative,
        Medium,
    }
    Message ReplyMessage(Member member, MessageChain input);


    /**
     * 对戳戳的回复
     * @param userOrBot 发送者
     * @param input 输入
     * @return 输出
     */
    String ReplyTick(UserOrBot userOrBot, String input);
}
