package com.cxgitbot;

import com.cxgitbot.proxies.SeparatorProxy;
import com.cxgitbot.utils.IConfigurable;
import com.cxgitbot.utils.ISeparator;
import com.cxgitbot.utils.IReply;
import com.cxgitbot.utils.impl.Configurable;
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
    public final IConfigurable _configure = new Configurable();
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
           if(f.getSender().getId() == Core.AuthorId) {
                switch (f.getMessage().contentToString()){
                    case "/help":
                        f.getSender().sendMessage("/配置状态 用于显示当前配置的状态\n" +
                                "/配置 用户开启配置\n" +
                                "/取消 放弃当前进行的配置\n" +
                                "/确认 确认当前的配置\n");
                        break;
                    case "/配置状态":
                        f.getSender().sendMessage(_configure.StatusToString());
                        break;
                    case "/配置":
                        IConfigurable.Status s = _configure.Start();
                        f.getSender().sendMessage(_configure.StatusToString());
                        break;
                    case "/取消":
                        _configure.Reset();
                        f.getSender().sendMessage(_configure.StatusToString());
                        break;
                    case "/确认":
                        _configure.Finish();
                        f.getSender().sendMessage(_configure.StatusToString());
                    default:
                        switch (_configure.CurrentStatus()){
                            case WaitingForKey:
                                _configure.Configure(f.getMessage().contentToString());
                                f.getSender().sendMessage(_configure.StatusToString());
                                break;
                            case WaitingForValue:
                                _configure.Configure(f.getMessage());
                                f.getSender().sendMessage(_configure.StatusToString());
                                break;
                            case Nothing:
                                f.getSender().sendMessage(_configure.Trigger(f.getMessage()));
                        }
                }
           }
        });
    }
}