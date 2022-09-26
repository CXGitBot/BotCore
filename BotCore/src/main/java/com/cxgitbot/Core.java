package com.cxgitbot;

import com.cxgitbot.utils.ISeparator;
import com.cxgitbot.utils.IReply;
import com.cxgitbot.utils.impl.Replier;
import com.cxgitbot.working.SeparatorDefault;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.NudgeEvent;

public final class Core extends JavaPlugin {
    public static final Core INSTANCE = new Core();
    private static  final IReply replier = new Replier();

    private final ISeparator _groupSeparator = new SeparatorDefault();

    private Core() {
        super(new JvmPluginDescriptionBuilder("com.cxgitbot.core", "0.1.0")
                .name("Core")
                .author("Feast")
                .build());
    }
    private static  final  long QunId = 631743342;
    @Override
    public void onEnable() {
        getLogger().info("Core 插件启动");
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            getLogger().info(g.getMessage().contentToString());
            if(g.getGroup().getId()==QunId ) {
                _groupSeparator.Receive(g);


                String msg = g.getMessage().contentToString();
                String reply = replier.ReplyMessage(g.getSender(), msg);
                if(!reply.equals("")) {
                    g.getGroup().sendMessage(reply);
                }
            }
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(NudgeEvent.class, n -> {
            //群戳戳
            if(n.getBot().getId() == n.getTarget().getId() && n.getSubject().getId() == QunId) {
                String reply = replier.ReplyTick(n.getFrom(), n.getAction());
                n.getSubject().sendMessage(reply);
            }
        });
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            getLogger().info(f.getMessage().contentToString());
        });
    }
}