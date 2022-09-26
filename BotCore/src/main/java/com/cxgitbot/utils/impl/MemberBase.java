package com.cxgitbot.utils.impl;

import com.cxgitbot.utils.IGroup;
import com.cxgitbot.utils.IMember;
import net.mamoe.mirai.contact.Member;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class MemberBase implements IMember {
    private final static HashMap<Long,MemberBase> Groupful = new HashMap<>();
    private final static HashMap<Long,MemberBase> Groupless = new HashMap<>();
    protected final Member _member;
    protected final Set<IGroup> Groups;
    protected MemberBase(Member member,IGroup belong){
        MemberBase cache = Groupful.get(member.getId());
        if(cache == null){
            Groups = new HashSet<>();
            Groupful.put(member.getId(),this);
        }else{
            Groups = cache.Groups;
        }
        _member = member;
        Groups.add(belong);
    }
    protected MemberBase(Member member){
        MemberBase cache = Groupful.get(member.getId());
        if(cache == null){
            Groups = new HashSet<>();
            Groupless.put(member.getId(),this);
        }else{
            Groups = cache.Groups;
        }
        _member = member;
    }
    @Override
    public Member Member() {
        return _member;
    }
    @Override
    public long Id() {
        return _member.getId();
    }
    @Override
    public Set<IGroup> GetGroups() {return Groups;}
}
