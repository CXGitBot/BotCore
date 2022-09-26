package com.cxgitbot.utils;

import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;

public interface IReply {
    enum Tension{
        Positive,
        Negative,
        Medium,
    }
    String Reply(Member member, String input);
    String Reply(UserOrBot userOrBot,String input);
}
