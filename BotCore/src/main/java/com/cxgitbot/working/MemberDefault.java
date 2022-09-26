package com.cxgitbot.working;

import com.cxgitbot.Core;
import com.cxgitbot.utils.IGroup;
import com.cxgitbot.utils.impl.MemberBase;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;


public class MemberDefault extends MemberBase {
    public MemberDefault(Member member, IGroup belong) {
        super(member,belong);
    }
    public MemberDefault(Member member){
        super(member);
    }

    @Override
    public void Receive(GroupMessageEvent message) {
        Core.INSTANCE.getLogger().info("无任何逻辑的人员消息");
        Core.INSTANCE.getLogger().info("内容是:" + message.getMessage().contentToString());
    }

    @Override
    public void Nudge(NudgeEvent nudge) {
        Core.INSTANCE.getLogger().info("无任何逻辑的戳一戳消息");
        Core.INSTANCE.getLogger().info( "被敲的人是:" + nudge.getTarget());
        Core.INSTANCE.getLogger().info( "getTarget的类型是:" + nudge.getTarget().getClass().getName());
    }
}
