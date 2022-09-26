package com.cxgitbot.utils;

import net.mamoe.mirai.contact.Group;

import java.util.Collection;

/**
 * 对组消息的分发
 */
public interface ISeparator extends IMessage {
    IGroup Create(Group group);
    IGroup Get(Group group);
    IGroup Get(long id);
    Collection<IGroup> GetAll();
}
