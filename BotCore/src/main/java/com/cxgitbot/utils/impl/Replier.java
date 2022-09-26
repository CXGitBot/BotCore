package com.cxgitbot.utils.impl;

import com.cxgitbot.Core;
import com.cxgitbot.utils.IAnalyzer;
import com.cxgitbot.utils.IReply;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.UserOrBot;

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
    public String ReplyMessage(Member member, String input) {
        Tension t = analyzer.AnalyzeTension(input);
        String ret  = "";
        if(input.equals("莎士比亚曾经说过")){
            return "安全通訊協定出現錯誤，無法建立與伺服器的安全連接。";
        }
        if(input.contains("Bot")){
            if(input.contains("测试")){
                switch (t){
                    case Positive:
                    case Medium:
                        ret = "你满意了吧";
                        break;
                    case Negative:
                        ret = "不测拉倒";
                }
                return ret;
            }
            if(input.contains("Bot") && ( input.contains("地址") || input.contains("git") || input.contains("Git"))){
                switch (t) {
                    case Positive:
                    case Medium:
                        ret = "我核心的地址是：\n https://github.com/CXGitBot/BotCore \n" + "欢迎对我进行构建~";
                        break;
                    case Negative:
                        ret  = "¿";
                }
                return ret;
            }
            if(member.getId()== Core.AuthorId){
                if(input.contains("重启")){
                    return ret = "好嘞";
                }
            }
        }
        if(member.getId()== Core.AuthorId){
            if(input.contains("来人")){
                return ret = "来了嗷";
            }
        }
        if(input.contains("666")){
            return ret = input;
        }
        if(input.equals("?")||input.equals("？")){
            return ret = "¿";
        }
        if(input.equals("¿")){
            return ret = "?";
        }
        return ret;
    }

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
