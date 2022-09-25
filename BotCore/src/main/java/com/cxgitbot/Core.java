package com.cxgitbot;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class Core extends JavaPlugin {
    public static final Core INSTANCE = new Core();

    private Core() {
        super(new JvmPluginDescriptionBuilder("com.cxgitbot.core", "0.1.0")
                .name("Core")
                .author("Administrator")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded!");
    }
}