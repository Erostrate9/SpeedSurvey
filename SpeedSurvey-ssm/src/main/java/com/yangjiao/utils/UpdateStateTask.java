package com.yangjiao.utils;

import com.yangjiao.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateStateTask {
    @Autowired
    private QuestionnaireService questionnaireService;

    @Scheduled(cron = "0 * * * * *") //每1分钟（秒数为0时）执行一次任务
    public void uptateQuestionnaireState() {
        questionnaireService.updateState();
    }
}
