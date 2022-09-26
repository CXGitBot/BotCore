package com.cxgitbot.utils.impl;

import com.cxgitbot.utils.IGroup;
import com.cxgitbot.utils.IMember;
import com.cxgitbot.working.MemberDefault;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;

import java.util.Collection;
import java.util.HashMap;

public abstract class GroupBase implements IGroup {
    protected final Group _group;
    public GroupBase(Group group){
        _group = group;
    }
    protected final HashMap<Long, IMember> Members = new HashMap<>();
    @Override
    public IMember Create(Member member) {
        return new MemberDefault(member,this);
    }
    @Override
    public IMember Get(Member member) {
        return Get(member.getId());
    }
    @Override
    public IMember Get(Long id){
        return Members.get(id);
    }
    @Override
    public Group Group(){
        return _group;
    }
    @Override
    public Long GroupId(){
        return  Group().getId();
    }

    @Override
    public Collection<IMember> GetAll(){ return Members.values(); }

    protected IMember GetOrCreate(Member member){
        IMember f = Get(member);
        if (f == null) {
            f = Create(member);
            Members.put(member.getId(), f);
        }
        return f;
    }
}
