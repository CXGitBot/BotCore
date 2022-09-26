package com.cxgitbot.utils.impl;

import com.cxgitbot.utils.IAnalyzer;

public class Analyzer implements IAnalyzer {
    @Override
    public Replier.Tension AnalyzeTension(String content) {
        Replier.Tension ret = Replier.Tension.Medium;
        if(content.contains("不要")||content.contains("不是")||content.contains("不行")){
            ret = Replier.Tension.Negative;
        }else if(content.contains("好")||content.contains("行")){
            ret = Replier.Tension.Positive;
        }
        return ret;
    }
}
