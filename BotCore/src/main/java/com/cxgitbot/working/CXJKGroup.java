package com.cxgitbot.working;

import com.cxgitbot.Core;
import com.cxgitbot.utils.impl.GroupBase;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageRecallEvent;
import net.mamoe.mirai.event.events.NudgeEvent;
import net.mamoe.mirai.message.data.Message;

public class CXJKGroup extends GroupBase {
    public CXJKGroup(Group group) {
        super(group);
        Core.INSTANCE.getLogger().warning( "创建CX群"+group);
    }

    @Override
    public void Receive(GroupMessageEvent message) {
        Message ret = Core.replier.ReplyMessage(message.getSender(),message.getMessage());
       if(ret!=null)
           _group.sendMessage(ret);
    }

    @Override
    public void Nudge(NudgeEvent nudge) {
        if(nudge.getTarget().getId()==nudge.getBot().getId()){
            String ret = Core.replier.ReplyTick(nudge.getFrom(),nudge.getAction());
            if(!ret.equals("")){
                _group.sendMessage(ret);
            }
        }
    }
}
