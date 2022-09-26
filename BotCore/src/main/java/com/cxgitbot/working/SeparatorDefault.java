package com.cxgitbot.working;

import com.cxgitbot.Core;
import com.cxgitbot.utils.impl.SeparatorBase;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;


public class SeparatorDefault extends SeparatorBase {
    @Override
    public void Receive(GroupMessageEvent event) {
        Core.INSTANCE.getLogger().info("无任何逻辑的分发消息");
        Core.INSTANCE.getLogger().info( "所在群是:" + event.getGroup());
        GetOrCreate(event.getGroup()).Receive(event);
    }

    @Override
    public void Nudge(NudgeEvent nudge) {
        Core.INSTANCE.getLogger().info("无任何逻辑的戳一戳消息");
        Core.INSTANCE.getLogger().info( "所在群是:" + nudge.getSubject());
        Core.INSTANCE.getLogger().info( "getSubject的类型是:" + nudge.getSubject().getClass().getName());
        GetOrCreate((Group)nudge.getSubject()).Nudge(nudge);
    }
}
