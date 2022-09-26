package com.cxgitbot.utils.impl;

import com.cxgitbot.working.GroupDefault;
import com.cxgitbot.utils.IGroup;
import com.cxgitbot.utils.ISeparator;
import net.mamoe.mirai.contact.Group;

import java.util.Collection;
import java.util.HashMap;

public abstract class SeparatorBase implements ISeparator {
    protected final HashMap<Long, IGroup> Groups = new HashMap<>();
    protected SeparatorBase(){}

    @Override
    public IGroup Create(Group group) {
        return new GroupDefault(group);
    }
    @Override
    public IGroup Get(Group group) {
        return Get(group.getId());
    }
    @Override
    public IGroup Get(long id) {
        return Groups.get(id);
    }

    @Override
    public Collection<IGroup> GetAll(){
        return Groups.values();
    }

    protected IGroup GetOrCreate(Group group) {
        IGroup f = Get(group);
        if (f == null) {
            f = Create(group);
            Groups.put(f.GroupId(), f);
        }
        return f;
    }
}
