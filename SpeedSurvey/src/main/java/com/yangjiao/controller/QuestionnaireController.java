package com.yangjiao.controller;

import com.yangjiao.domain.*;
import com.yangjiao.service.OptionService;
import com.yangjiao.service.QuestionService;
import com.yangjiao.service.QuestionnaireService;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private OptionService optionService;

    @PostMapping("/create")
    @ResponseBody
    public Map<String, Object> doCreate(@RequestBody Questionnaire questionnaire) {
        if (questionnaire.getUserId() == null) {
            return ResponseData.create().error("问卷必须属于某发布者").getResponse();
        }
        if (questionnaire.getTitle() == null || "".equals(questionnaire.getTitle())) {
            return ResponseData.create().error("问卷必须持有标题").getResponse();
        }
        Questionnaire questionnaireCreated = questionnaireService.create(questionnaire);
        if (questionnaireCreated == null) {
            return ResponseData.create().error("创建失败").getResponse();
        }
        return ResponseData.create().success("创建成功").setData(questionnaireCreated).getResponse();

    }

    @GetMapping("/end")
    @ResponseBody
    public Map<String, Object> doEnd(int id) {
        Questionnaire questionnaire = questionnaireService.endByID(id);
        if (questionnaire == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(questionnaire).getResponse();

    }



    @GetMapping("/query")
    @ResponseBody
    public Map<String, Object> doQuery(int publisherId) {
        List<Questionnaire> questionnaires = questionnaireService.queryALlByPublisherId(publisherId);
        if (questionnaires == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(questionnaires).getResponse();
    }

    @GetMapping("/queryAllInPublic")
    @ResponseBody
    public Map<String, Object> doQueryAllInPublic() {
        List<Questionnaire> questionnaires = questionnaireService.queryAllInPublic();
        if (questionnaires == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(questionnaires).getResponse();
    }

    @GetMapping("/queryDetail")
    @ResponseBody
    public Map<String, Object> doQueryDetail(int id) {
        Questionnaire questionnaire = questionnaireService.queryById(id);
        if (questionnaire == null) {
            return ResponseData.create().error().getResponse();
        }

        QuestionnaireComplete questionnaireComplete = new QuestionnaireComplete(questionnaire);
        List<Question> questions = questionService.queryALlByQuestionnaireId(id);
        List<QuestionComplete> questionCompletes = new ArrayList<>();
        for (Question question : questions) {
            QuestionComplete questionComplete = new QuestionComplete(question);
            //为单选/多选题 则加入选项信息
            if (question.getType() != 0) {
                List<Option> options = optionService.queryALlByQuestionId(question.getId());
                questionComplete.setOptions(options);
            }
            questionCompletes.add(questionComplete);
        }
        questionnaireComplete.setQuestionCompletes(questionCompletes);
        return ResponseData.create().success().setData(questionnaireComplete).getResponse();

    }

    @GetMapping("/queryDetailByUrl")
    @ResponseBody
    public Map<String, Object> doQueryDetail(String url) {
        Questionnaire questionnaire = questionnaireService.queryByUrl(url);
        if (questionnaire == null) {
            return ResponseData.create().error().getResponse();
        }

        QuestionnaireComplete questionnaireComplete = new QuestionnaireComplete(questionnaire);
        List<Question> questions = questionService.queryALlByQuestionnaireId(questionnaire.getId());
        List<QuestionComplete> questionCompletes = new ArrayList<>();
        for (Question question : questions) {
            QuestionComplete questionComplete = new QuestionComplete(question);
            //为单选/多选题 则加入选项信息
            if (question.getType() != 0) {
                List<Option> options = optionService.queryALlByQuestionId(question.getId());
                questionComplete.setOptions(options);
            }
            questionCompletes.add(questionComplete);
        }
        questionnaireComplete.setQuestionCompletes(questionCompletes);
        return ResponseData.create().success().setData(questionnaireComplete).getResponse();

    }
}
