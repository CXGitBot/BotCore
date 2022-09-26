package com.cxgitbot.proxies;

import com.cxgitbot.utils.IReply;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public abstract class ReplyProxyBase implements InvocationHandler , IReply {
    protected final IReply _proxy;
    protected final IReply _real;

    protected ReplyProxyBase(IReply proxy){
        _real = proxy;
        ClassLoader loader = proxy.getClass().getClassLoader();
        Class[] interfaces = proxy.getClass().getInterfaces();
        _proxy = (IReply) Proxy.newProxyInstance(loader, interfaces, this);
    }

    public abstract Object invoke(Object proxy, Method method, Object[] args) throws Throwable ;

}
