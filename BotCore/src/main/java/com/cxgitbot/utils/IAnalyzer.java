package com.cxgitbot.utils;

import com.cxgitbot.utils.impl.Replier;

public interface IAnalyzer {
    Replier.Tension AnalyzeTension(String content);
}
