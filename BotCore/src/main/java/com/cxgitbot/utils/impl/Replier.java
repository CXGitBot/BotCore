package com.cxgitbot.utils.impl;

import com.cxgitbot.Core;
import com.cxgitbot.utils.IAnalyzer;
import com.cxgitbot.utils.IReply;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import org.graalvm.compiler.graph.Node;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Replier implements IReply {
    IAnalyzer analyzer  = new Analyzer();
    public Replier(){
        List<String> lstrs = new ArrayList<>();
        lstrs.add("你干嘛啊~哈哈~哎呦");
        lstrs.add("哈戳戳");
        lstrs.add("干嘛");

        List<String> mstrs = new ArrayList<>();
        mstrs.add("你们不要再戳了啦");
        mstrs.add("给你机会你不中用啊");

        List<String> hstrs = new ArrayList<>();
        hstrs.add("很快到你家门口");
        hstrs.add("你是故意找茬是不是");
        hstrs.add("Aughhhhh");

        Levels.put(1,lstrs);
        Levels.put(2,mstrs);
        Levels.put(3,hstrs);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
               TickMembers.forEach((k,v)->{
                   Duration d = Duration.between(v.LastTime, LocalDate.now());
                   if(d.getSeconds() > 60){
                       v.Count = 0;
                   }
               });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 5, 1000 * 60);
    }
    @Override
    public Message ReplyMessage(Member member, MessageChain msg) {
        if(msg.contains(Image.Key)){
            Image i = msg.get(Image.Key);
            if(Images.size()>50){
                Images.remove(0);
            }
            Images.add(i);
            Core.INSTANCE.getLogger().info("存了一个表情包："+i.contentToString());
        }
        String input = msg.contentToString();
        String ret  = "";
        ret = NormalInput(input);
        if(!ret.equals("")){
            return new MessageChainBuilder().append(ret).build();
        }
        if(input.equals("寄")||input.contains("寄了")){
            return new MessageChainBuilder()
                    .append(Image.fromId("{C703B9F9-F9F9-CFB1-8E17-BCB9B2D63E5F}.gif"))
                    .build();
        }
        ret = FunctionInput(member,input);
        if(!ret.equals("干嘛") && !ret.equals("")){
            return new MessageChainBuilder().append(ret).build();
        }else if(ret.equals("干嘛")){
            return new MessageChainBuilder()
                    .append(Images.get(new Random().nextInt(Images.size())))
                    .build();
        }
        return null;
    }
    private String FunctionInput(Member member, String input){
        Tension t = analyzer.AnalyzeTension(input);
        String ret  = "";
        if(input.contains("Bot")){
            if(input.contains("测试")){
                switch (t){
                    case Positive:
                    case Medium:
                        return ret = "你满意了吧";
                        case Negative:
                        return ret = "不测拉倒";
                }
            }
            if(input.contains("Bot") && ( input.contains("地址") || input.contains("git") || input.contains("Git"))){
                switch (t) {
                    case Positive:
                    case Medium:
                        return  ret = "我核心的地址是：\n https://github.com/CXGitBot/BotCore \n" + "欢迎对我进行构建~";
                    case Negative:
                        return  ret  = "¿";
                }
            }
            if(member.getId()== Core.AuthorId){
                if(input.contains("重启")){
                    return ret = "好嘞";
                }
            }
            return ret = "干嘛";
        }
        if(member.getId()== Core.AuthorId){
            if(input.contains("来人")){
               return ret = "来了嗷";
            }
        }
        return ret;
    }
    private String NormalInput(String input) {
        String ret = "";
        if (input.equals("莎士比亚曾经说过")) {
            return ret = "安全通訊協定出現錯誤，無法建立與伺服器的安全連接。";
        }
        if (input.contains("666")) {
            return ret = input;
        }
        if (input.equals("?") || input.equals("？")) {
            return ret = "¿";
        }
        if (input.equals("¿")) {
            return ret = "?";
        }
        return ret;
    }

    private List<Image> Images = new ArrayList<>();

    static class TickObject{
        int Count;
        LocalDate LastTime;
        UserOrBot Sender;
    }

    private static final HashMap<Long,TickObject> TickMembers = new HashMap<>();
    private static final HashMap<Integer, List<String>> Levels = new HashMap<>();
    @Override
    public String ReplyTick(UserOrBot userOrBot, String input) {
        String ret = "";
        if(TickMembers.containsKey(userOrBot.getId())){
            TickObject tick = TickMembers.get(userOrBot.getId());
            if(tick.Count < 3 ){
                tick.Count++;
            }
            tick.LastTime = LocalDate.now();
            Random r=new Random();
            ret = Levels.get(tick.Count).get(r.nextInt(Levels.get(tick.Count).size()));
        }else{
            TickObject tick = new TickObject();
            tick.Count = 1;
            tick.LastTime = LocalDate.now();;
            TickMembers.put(userOrBot.getId(),tick);
            Random r=new Random();
            ret = Levels.get(tick.Count).get(r.nextInt(Levels.get(tick.Count).size()));
        }
        return ret;
    }
}
