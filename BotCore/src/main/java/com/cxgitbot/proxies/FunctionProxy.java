package com.cxgitbot.proxies;

import com.cxgitbot.utils.IReply;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;

import java.lang.reflect.Method;

public class FunctionProxy extends ReplyProxyBase{
    protected FunctionProxy(IReply proxy) {
        super(proxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(_real,args);
    }

    @Override
    public String ReplyMessage(Member member, String input) {
        return _proxy.ReplyMessage(member,input);
    }

    @Override
    public String ReplyTick(UserOrBot userOrBot, String input) {
        return _proxy.ReplyTick(userOrBot,input);
    }
}
