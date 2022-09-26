package com.cxgitbot.utils;

import net.mamoe.mirai.contact.Member;

public interface ICache {
    void Add(Member member);
    int ChatCount(Member member);
}
