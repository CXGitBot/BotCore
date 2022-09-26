package com.cxgitbot;

import com.cxgitbot.proxies.SeparatorProxy;
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
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

public final class Core extends JavaPlugin {
    public static final Core INSTANCE = new Core();
    public static  final IReply replier = new Replier();

    private final ISeparator _groupSeparator = new SeparatorProxy();

    private Core() {
        super(new JvmPluginDescriptionBuilder("com.cxgitbot.core", "0.1.0")
                .name("Core")
                .author("Feast")
                .build());
    }
    public static final long QunId = 631743342L;
    public static final long AuthorId = 2464939515L;
    @Override
    public void onEnable() {
        getLogger().info("Core 插件启动");
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, _groupSeparator::Receive);
        GlobalEventChannel.INSTANCE.subscribeAlways(NudgeEvent.class, _groupSeparator::Nudge);
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            getLogger().info(f.getMessage().contentToString());
           if(f.getMessage().contains(Image.Key)) {
               Image i = f.getMessage().get(Image.Key);
               MessageChain builder = new MessageChainBuilder()
                       //.append(Image.fromId("{AE454B71-6F72-D12A-9DA9-6BF75DA56012}.jpg"))
                       .append(Image.fromId(i.getImageId()))
                       .build();
               if (f.getSender().getId() == Core.AuthorId) {
                   f.getSender().sendMessage(builder);
               }
           }


        });
    }
}