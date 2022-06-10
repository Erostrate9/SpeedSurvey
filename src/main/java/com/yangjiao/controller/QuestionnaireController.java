package com.yangjiao.controller;

import com.yangjiao.domain.*;
import com.yangjiao.service.*;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
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
    @Autowired
    private TextResultService textResultService;
    @Autowired
    private OptionResultService optionResultService;

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
    public Map<String, Object> doQuery(int userId) {
        List<Questionnaire> questionnaires = questionnaireService.queryALlByPublisherId(userId);
        if (questionnaires == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(questionnaires).getResponse();
    }

    // *********************************** Speed-Survey ***********************************
    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> doAdd(@RequestBody Map<String, Object> questionnaireMap, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return ResponseData.create().error("未登录").getResponse();
        }
        String title = (String) questionnaireMap.get("title");
        Integer id = (Integer) questionnaireMap.get("id");
        String desc = (String) questionnaireMap.get("desc");
        Boolean ipRestrict = (Boolean) questionnaireMap.get("ipRestrict");
        Integer org = (Integer) questionnaireMap.get("org");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setTitle(title);
        questionnaire.setUserId(user.getId());
        questionnaire.setDescription(desc);
        if (ipRestrict) {
            questionnaire.setIpLimit(1);
        } else {
            questionnaire.setIpLimit(0);
        }
        questionnaire.setBelongOrganization(org);
        if (id == null || id == 0) { //创建新闻卷
            Questionnaire questionnaireCreate = questionnaireService.create(questionnaire);
            if (questionnaireCreate == null) {
                return ResponseData.create().error("问卷创建/修改成功").getResponse();
            }
        } else { //修改问卷
            questionnaire.setId(id);
            Questionnaire questionnaireUpdate = questionnaireService.update(questionnaire);
            if (questionnaireUpdate == null) {
                return ResponseData.create().error("问卷创建/修改成功").getResponse();
            }
        }

        return ResponseData.create().success("问卷创建/修改成功").getResponse();


    }

    @GetMapping("/creater/list")
    @ResponseBody
    public Map<String, Object> getList(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return ResponseData.create().error("用户未登录").getResponse();
        }
        Integer userId = user.getId();
        List<Questionnaire> questionnaires = questionnaireService.queryALlByPublisherId(userId);
        if (questionnaires == null) {
            return ResponseData.create().error().getResponse();
        }
        List<Map<String, Object>> questionnaireSimples = new ArrayList<>();
        for (Questionnaire questionnaire : questionnaires) {
            HashMap<String, Object> questionnaireSimple = new HashMap<String, Object>();
            questionnaireSimple.put("id", questionnaire.getId());
            questionnaireSimple.put("title", questionnaire.getTitle());
            questionnaireSimple.put("desc", questionnaire.getDescription());
            questionnaireSimple.put("status", questionnaire.getState());
            questionnaireSimples.add(questionnaireSimple);
        }
        return ResponseData.create().success().setData(questionnaireSimples).getResponse();
    }

    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> doDelete(int wjId) {
        Questionnaire questionnaire = questionnaireService.deleteById(wjId);
        if (questionnaire == null) {
            return ResponseData.create().error("找不到该问卷").getResponse();
        }
        return ResponseData.create().success("success").getResponse();
    }

    @PostMapping("/push")
    @ResponseBody
    public Map<String, Object> doPush(@RequestParam("wjId") int wjId, @RequestParam("status") int status) {
        Questionnaire questionnaire = questionnaireService.updateStateById(wjId, status);
        if (questionnaire == null) {
            return ResponseData.create().success("问卷状态修改失败").getResponse();
        }
        return ResponseData.create().success("问卷状态修改成功").getResponse();
    }

    @GetMapping("/analysis")
    @ResponseBody
    public Map<String, Object> doAnalysis(int wjId) {
        if (questionnaireService.queryById(wjId) == null) {
            return ResponseData.create().error("该问卷不存在").getResponse();
        }

        List<Question> questions = questionService.queryALlByQuestionnaireId(wjId);

        List<Map<String, Object>> analysisResultMaps = new ArrayList<>();
        for (Question question : questions) {
            Map<String, Object> analysisResultMap = new HashMap<>();
            analysisResultMap.put("title", question.getTitle());
            analysisResultMap.put("type", question.getType());
            analysisResultMap.put("questionId", question.getId());
            if (question.getType() == 0) { //填空题
                analysisResultMap.put("result", "");
            } else { //单选/多选题
                List<Option> options = optionService.queryALlByQuestionId(question.getId());
                List<Map<String, Object>> resultMaps = new ArrayList<>();
                int totalCount = 0;
                for (Option option : options) {
                    totalCount += option.getCount();
                }
                for (Option option : options) {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("option", option.getContent());
                    int count = option.getCount();
                    resultMap.put("count", count);
                    resultMap.put("percent", totalCount != 0 ? count / totalCount : 0);
                    resultMaps.add(resultMap);
                }
                analysisResultMap.put("result", resultMaps);

            }
            analysisResultMaps.add(analysisResultMap);
        }
        return ResponseData.create().success().setData(analysisResultMaps).getResponse();
    }


    // *********************************** Speed-Survey ***********************************


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

    @PostMapping("/submit")
    @ResponseBody
    public Map<String, Object> doSubmit(@RequestBody AnswerComplete answerComplete) {
        Integer userId = answerComplete.getUserId();
        List<Answer> answers = answerComplete.getAnswers();
        System.out.println(answerComplete);
        for (Answer answer : answers) {
            if (answer.getType() == 0) { //填空题
                TextResult textResult = new TextResult();
                textResult.setUserId(userId);
                textResult.setQuestionId(answer.getQuestionId());
                textResult.setAnswer(answer.getText());
                TextResult textResultAdd = textResultService.add(textResult);
                if (textResultAdd == null) {
                    return ResponseData.create().error().getResponse();
                }
            } else if (answer.getType() == 1) { //单选题
                OptionResult optionResult = new OptionResult();
                optionResult.setUserId(userId);

                //更新选项表的count字段
                int optionId = answer.getOptionId();
                Option option = optionService.queryById(optionId);
                option.setCount(option.getCount() + 1);
                optionService.update(option);


                optionResult.setOptionId(answer.getOptionId());
                OptionResult optionResultAdd = optionResultService.add(optionResult);
                if (optionResultAdd == null) {
                    return ResponseData.create().error().getResponse();
                }
            } else if (answer.getType() == 2) { //多选题
                for (int optionId : answer.getOptionIds()) {
                    OptionResult optionResult = new OptionResult();
                    optionResult.setUserId(userId);
                    optionResult.setOptionId(optionId);
                    OptionResult optionResultAdd = optionResultService.add(optionResult);
                    if (optionResultAdd == null) {
                        return ResponseData.create().error().getResponse();
                    }
                }
            }
        }
        return ResponseData.create().success("已成功提交问卷").getResponse();
    }


}
