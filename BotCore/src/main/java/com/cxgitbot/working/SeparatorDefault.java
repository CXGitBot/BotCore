package com.cxgitbot.working;

import com.cxgitbot.utils.impl.SeparatorBase;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import static sun.font.FontUtilities.getLogger;

public class SeparatorDefault extends SeparatorBase {
    @Override
    public void Receive(GroupMessageEvent event) {
        getLogger().info("无任何逻辑的分发消息");
        getLogger().info( "所在群是" + event.getGroup());
        GetOrCreate(event.getGroup()).Receive(event);
    }
}
