package com.cxgitbot.utils.impl;

import com.cxgitbot.utils.IMember;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public abstract class MemberBase implements IMember {
    protected final Member _member;
    protected MemberBase(Member member){
        _member = member;
    }

    @Override
    public Member Member() {
        return _member;
    }

}
