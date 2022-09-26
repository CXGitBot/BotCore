package com.cxgitbot.utils.impl;

import com.cxgitbot.utils.IGroup;
import com.cxgitbot.utils.IMember;
import net.mamoe.mirai.contact.Member;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class MemberBase implements IMember {
    static protected class MemberInternal {
        protected final Member Member;
        protected final Set<IGroup> Groups = new HashSet<>();
        private MemberInternal(Member member){
            Member = member;
        }
    }
    private final MemberInternal info;
    private final static HashMap<Long,MemberBase> Groupful = new HashMap<>();
    private final static HashMap<Long,MemberBase> Groupless = new HashMap<>();

    protected MemberBase(Member member,IGroup belong){
        MemberBase cache = Groupful.get(member.getId());
        if(cache == null){
            info = new MemberInternal(member);
            Groupful.put(member.getId(),this);
        }else{
            info = cache.info;
        }
        info.Groups.add(belong);
    }
    protected MemberBase(Member member){
        MemberBase cache = Groupful.get(member.getId());
        if(cache == null){
            info = new MemberInternal(member);
            Groupless.put(member.getId(),this);
        }else{
            info = cache.info;
        }
    }
    @Override
    public Member Member() {
        return info.Member;
    }
    @Override
    public long Id() {
        return info.Member.getId();
    }
    @Override
    public Set<IGroup> GetGroups() {return info.Groups;}

}
