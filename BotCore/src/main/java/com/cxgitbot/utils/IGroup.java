package com.cxgitbot.utils;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;

import java.util.Collection;

public interface IGroup extends IMessage {
    IMember Create(Member member);
    IMember Get(Member member);
    IMember Get(Long id);
    Group Group();
    Long GroupId();
    Collection<IMember> GetAll();
}
