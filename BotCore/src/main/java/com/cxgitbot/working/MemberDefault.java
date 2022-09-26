package com.cxgitbot.working;

import com.cxgitbot.utils.IGroup;
import com.cxgitbot.utils.impl.MemberBase;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import static sun.font.FontUtilities.getLogger;

public class MemberDefault extends MemberBase {
    public MemberDefault(Member member, IGroup belong) {
        super(member,belong);
    }
    public MemberDefault(Member member){
        super(member);
    }
    @Override
    public void Receive(GroupMessageEvent message) {
        getLogger().info("无任何逻辑的人员消息");
        getLogger().info("内容是:" + message.getMessage().contentToString());
    }
}
