package com.cxgitbot.working;

import com.cxgitbot.Core;
import com.cxgitbot.utils.impl.GroupBase;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;


public class GroupDefault extends GroupBase {
    public GroupDefault(Group group) {super(group);}

    @Override
    public void Receive(GroupMessageEvent message) {
        Core.INSTANCE.getLogger().info("无任何逻辑的组消息");
        Core.INSTANCE.getLogger().info( "发送者是:" + message.getSenderName());
        GetOrCreate(message.getSender()).Receive(message);
    }

    @Override
    public void Nudge(NudgeEvent nudge) {
        Core.INSTANCE.getLogger().info("无任何逻辑的戳一戳消息");
        Core.INSTANCE.getLogger().info( "发送者是:" + nudge.getFrom());
        Core.INSTANCE.getLogger().info( "getFrom的类型是:" + nudge.getFrom().getClass().getName());
        GetOrCreate((Member) nudge.getFrom()).Nudge(nudge);
    }
}
