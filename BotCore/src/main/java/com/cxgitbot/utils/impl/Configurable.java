package com.cxgitbot.utils.impl;

import com.cxgitbot.utils.IConfigurable;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.HashMap;
import java.util.Set;

public class Configurable implements IConfigurable {
    private HashMap<String,MessageChain> Configs = new HashMap<>();
    private Status Current = Status.Nothing;
    private String CurrentKey;
    private MessageChain CurrentValue;
    @Override
    public Status CurrentStatus() {
        return Current;
    }

    @Override
    public Status Start() {
        if(Current != Status.Nothing){
            return Current;
        }
        return Current = Status.WaitingForKey;
    }

    @Override
    public void Configure(String key) {
        if(Current == Status.WaitingForKey){
            if(Configs.containsKey(key)){
                return;
            }
            CurrentKey = key;
        }else if(Current == Status.WaitingForValue){
            CurrentKey = key;
        }
        Current = Status.WaitingForValue;
    }

    @Override
    public void Configure(MessageChain value) {
        if(Current == Status.WaitingForValue){
            CurrentValue = value;
        }else if(Current == Status.Configured){
            CurrentValue = value;
        }
        Current = Status.Configured;
    }


    @Override
    public Status Next() {
        return null;
    }

    @Override
    public void Reset() {
        CurrentKey = null;
        CurrentValue = null;
        Current = Status.Nothing;
    }

    @Override
    public void Finish() {
        if(Current == Status.Configured){
            Configs.put(CurrentKey,CurrentValue);
            CurrentKey = null;
            CurrentValue = null;
        }else{
            return;
        }
        Current = Status.Nothing;
    }

    @Override
    public Set<String> Keys() {
        return Configs.keySet();
    }

    @Override
    public MessageChain Trigger(MessageChain input) {
        return Configs.get(input.contentToString().trim());
    }

    @Override
    public String StatusToString() {
        String ret = "";
        switch (Current){
            case Nothing:
                ret = "当前可配置";
                break;
            case WaitingForKey:
                ret = "等待输入键";
                break;
            case WaitingForValue:
                ret = "等待输入值";
                break;
            case Configured:
                ret = "配置完成,请确认";
                break;
        }
        return ret;
    }
}
