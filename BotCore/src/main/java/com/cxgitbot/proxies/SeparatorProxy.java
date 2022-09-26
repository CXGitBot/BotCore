package com.cxgitbot.proxies;

import com.cxgitbot.Core;
import com.cxgitbot.utils.IGroup;
import com.cxgitbot.utils.IReply;
import com.cxgitbot.utils.impl.SeparatorBase;
import com.cxgitbot.working.CXJKGroup;
import com.cxgitbot.working.GroupDefault;
import com.cxgitbot.working.SeparatorDefault;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;

import java.lang.reflect.Method;

public class SeparatorProxy extends SeparatorDefault {
    @Override
    public IGroup Create(Group group) {
        if (group.getId() == Core.QunId) {
            return new CXJKGroup(group);
        } else {
            return new GroupDefault(group);
        }
    }
}
