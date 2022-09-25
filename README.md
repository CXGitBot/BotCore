# BotCore

这会是我的核心，构建需要足够健壮

## 简介

编写语言采用`Java`或`Kotlin`，可以采用其他语言进行拓展  
生成后的`Jar`包将会被置于Bot的插件目录下

## 接口

插件需要统一继承自`JavaPlugin`基类  
重载 `onEnable` 作为插件启动的入口点  
注册Bot的 `消息循环事件` 作为业务逻辑的开始
```java
class Template extends JavaPlugin{
    public Template(){
        super(...);
    }
    public override onEnable(){
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, g -> {
            g ... ;
        });
    }
}
```