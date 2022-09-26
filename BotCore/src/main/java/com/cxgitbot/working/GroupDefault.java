package com.cxgitbot.working;

import com.cxgitbot.utils.impl.GroupBase;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import static sun.font.FontUtilities.getLogger;


public class GroupDefault extends GroupBase {
    public GroupDefault(Group group) {
        super(group);
    }

    @Override
    public void Receive(GroupMessageEvent message) {
        getLogger().info("无任何逻辑的组消息");
        getLogger().info( "发送者是" + message.getSenderName());
        GetOrCreate(message.getSender()).Receive(message);
    }
}
