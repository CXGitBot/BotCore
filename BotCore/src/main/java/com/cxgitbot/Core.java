package com.cxgitbot;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;

public final class Core extends JavaPlugin {
    public static final Core INSTANCE = new Core();

    private Core() {
        super(new JvmPluginDescriptionBuilder("com.cxgitbot.core", "0.1.0")
                .name("Core")
                .author("Feast")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
            //监听群消息
            getLogger().info(g.getMessage().contentToString());
            if(g.getGroup().getId()==631743342){
                String msg = g.getMessage().contentToString();
                if(msg.contains("Bot测试")){
                    g.getGroup().sendMessage("你满意了吧");
                }
                if(msg.contains("Bot") && ( msg.contains("地址") || msg.contains("git") || msg.contains("Git"))){
                    g.getGroup().sendMessage("我核心的地址是：\n https://github.com/CXGitBot/BotCore \n" +
                        "欢迎对我进行构建~");
                }
            }

        });
        GlobalEventChannel.INSTANCE.subscribeAlways(FriendMessageEvent.class, f -> {
            //监听好友消息
            getLogger().info(f.getMessage().contentToString());
        });
    }
}