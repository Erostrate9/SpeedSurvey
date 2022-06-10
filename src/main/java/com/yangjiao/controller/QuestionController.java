package com.yangjiao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yangjiao.domain.Option;
import com.yangjiao.domain.Question;
import com.yangjiao.domain.QuestionComplete;
import com.yangjiao.service.OptionService;
import com.yangjiao.service.QuestionService;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private OptionService optionService;

    //@PostMapping("/add")
    //@ResponseBody
    //public Map<String, Object> doAdd(@RequestBody Question question) {
    //    if (question.getQuestionnaireId() == null) {
    //        return ResponseData.create().error("问题必须属于某问卷").getResponse();
    //    }
    //    if (question.getOrderNumber() == null) {
    //        return ResponseData.create().error("问题必须持有编号").getResponse();
    //    }
    //    if (question.getTitle() == null || "".equals(question.getTitle())) {
    //        return ResponseData.create().error("问题必须持有标题").getResponse();
    //    }
    //    if (question.getType() == null) {
    //        return ResponseData.create().error("问题必须持有类型").getResponse();
    //    }
    //    Question questionAdd = questionService.add(question);
    //
    //    if (questionAdd == null) {
    //        return ResponseData.create().error().getResponse();
    //    }
    //    return ResponseData.create().success().setData(questionAdd).getResponse();
    //
    //}

    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> doAdd(@RequestBody Map<String, Object> questionMap) {
        Integer wjId = (Integer) questionMap.get("wjId");
        Integer questionId = (Integer) questionMap.get("questionId");
        String title = (String) questionMap.get("title");
        Integer type = (Integer) questionMap.get("type");


        System.out.println(questionMap.get("options").getClass());

        Boolean isRequired = (Boolean) questionMap.get("isRequired");
        Boolean isPrivate = (Boolean) questionMap.get("isPrivate");

        Question question = new Question();
        question.setQuestionnaireId(wjId);
        question.setTitle(title);
        question.setType(type);
        Integer maxOrder = questionService.getMaxOrder(wjId);
        int orderNumber = 0;
        if (maxOrder == null) {
            orderNumber = 1;
        } else {
            orderNumber = maxOrder + 1;
        }
        question.setOrderNumber(orderNumber);
        if (isRequired) {
            question.setIsRequired(1);
        } else {
            question.setIsRequired(0);
        }
        if (isPrivate) {
            question.setIsPrivate(1);
        } else {
            question.setIsPrivate(0);
        }

        if (questionId == 0) { //新增问题
            Question questionAdd = questionService.add(question);
            if (questionAdd == null) {
                return ResponseData.create().error("问题创建/修改失败").getResponse();
            }
            questionId = questionAdd.getId();
        } else { //修改问题
            question.setId(questionId);
            Question questionUpdate = questionService.update(question);
            if (questionUpdate == null) {
                return ResponseData.create().error("问题创建/修改失败").getResponse();
            }
        }
        List<Integer> optionIntList = new ArrayList<Integer>();
        ArrayList<Map> optionMaps = (ArrayList<Map>) questionMap.get("options");
        for (Map optionMap : optionMaps) {
            optionIntList.add((Integer) optionMap.get("id"));
        }
        Integer[] optionInts = optionIntList.toArray(new Integer[optionIntList.size()]);
        //删除选项
        optionService.deleteNotInIds(optionInts);
        for (Map optionMap : optionMaps) {
            Integer optionId = (Integer) optionMap.get("id");
            String optionTitle = (String) optionMap.get("title");
            Option option = new Option();
            option.setContent(optionTitle);
            option.setQuestionId(questionId);

            if (optionId == 0) { //新增选项
                maxOrder = optionService.getMaxOrder(questionId);
                int optionOrder = 0;
                if (maxOrder == null) {
                    optionOrder = 1;
                } else {
                    optionOrder = maxOrder + 1;
                }
                option.setOrderNumber(optionOrder);
                Option optionAdd = optionService.add(option);
                if (optionAdd == null) {
                    return ResponseData.create().error("问题创建/修改失败").getResponse();
                }
            } else { //修改选项
                option.setId(optionId);
                Option optionUpdate = optionService.update(option);
                if (optionUpdate == null) {
                    return ResponseData.create().error("问题创建/修改失败").getResponse();
                }
            }
        }


        return ResponseData.create().success("问题创建/修改成功").getResponse();
    }

    @GetMapping("/delete")
    @ResponseBody
    public Map<String, Object> doDelete(int id) {
        Question question = questionService.deleteById(id);
        if (question == null) {
            return ResponseData.create().error("问题删除失败").getResponse();
        }
        return ResponseData.create().success("问题删除成功").getResponse();
    }

    @GetMapping("/query")
    @ResponseBody
    public Map<String, Object> doQuery(int questionnairId) {
        List<Question> questions = questionService.queryALlByQuestionnaireId(questionnairId);

        if (questions == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(questions).getResponse();
    }

    @GetMapping("/creater/list")
    @ResponseBody
    public Map<String, Object> getList(int wjId) {
        List<Map<String, Object>> questionMaps = new ArrayList<>();
        List<Question> questions = questionService.queryALlByQuestionnaireId(wjId);
        if (questions == null) {
            return ResponseData.create().error().getResponse();
        }
        for (Question question : questions) {
            HashMap<String, Object> questionMap = new HashMap<>();
            questionMap.put("title", question.getTitle());
            questionMap.put("type", question.getType());
            questionMap.put("id", question.getId());
            questionMap.put("must", question.getIsRequired() == 1);
            questionMap.put("isPrivate", question.getIsPrivate() == 1);
            if (question.getType() != 0) { //为单选/多选题 则加入选项信息
                List<Option> options = optionService.queryALlByQuestionId(question.getId());
                questionMap.put("row", options.size());
                List<Map<String, Object>> optionMaps = new ArrayList<>();
                for (Option option : options) {
                    HashMap<String, Object> optionMap = new HashMap<>();
                    optionMap.put("title", option.getContent());
                    optionMap.put("id", option.getId());
                    optionMaps.add(optionMap);
                }
                questionMap.put("options", optionMaps);
            } else { //填空题置row为1
                questionMap.put("row", 1);
            }
            questionMap.put("radioValue", -1);
            questionMap.put("checkboxValue", new ArrayList<>());
            questionMap.put("textValue", "");
            questionMaps.add(questionMap);
        }
        return ResponseData.create().success().setData(questionMaps).getResponse();
    }
}
