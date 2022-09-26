package com.cxgitbot.utils;

import net.mamoe.mirai.contact.Member;

import java.util.Set;

public interface IMember extends IMessage {
    Member Member();

    long Id();

    Set<IGroup> GetGroups();
}
