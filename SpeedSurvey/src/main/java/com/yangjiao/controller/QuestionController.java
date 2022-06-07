package com.yangjiao.controller;

import com.alibaba.fastjson.JSON;
import com.yangjiao.domain.Question;
import com.yangjiao.domain.Questionnaire;
import com.yangjiao.service.QuestionService;
import com.yangjiao.service.QuestionnaireService;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> doAdd(@RequestBody Question question) {
        if (question.getQuestionnaireId() == null) {
            return ResponseData.create().error("问题必须属于某问卷").getResponse();
        }
        if (question.getOrderNumber() == null) {
            return ResponseData.create().error("问题必须持有编号").getResponse();
        }
        if (question.getTitle() == null||"".equals(question.getTitle())) {
            return ResponseData.create().error("问题必须持有标题").getResponse();
        }
        if (question.getType() == null) {
            return ResponseData.create().error("问题必须持有类型").getResponse();
        }
        Question questionAdd = questionService.add(question);

        if (questionAdd == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(questionAdd).getResponse();

    }
    @GetMapping("/query")
    @ResponseBody
    public Map<String, Object> doQuery(int questionnairId){
        List<Question> questions = questionService.queryALlByQuestionnaireId(questionnairId);

        if (questions == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(questions).getResponse();

    }
}
