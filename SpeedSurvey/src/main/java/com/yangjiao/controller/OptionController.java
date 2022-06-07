package com.yangjiao.controller;

import com.alibaba.fastjson.JSON;
import com.yangjiao.domain.Option;
import com.yangjiao.service.OptionService;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

public class OptionController {
    @Autowired
    private OptionService optionService;

    @PostMapping("/add")
    @ResponseBody
    public Map<String, Object> doAdd(@RequestBody Option option) {
        if (option.getQuestionId() == null) {
            return ResponseData.create().error("选项必须属于某问题").getResponse();
        }
        if (option.getContent() == null||"".equals(option.getContent())) {
            return ResponseData.create().error("选项必须持有内容").getResponse();
        }
        if (option.getOrderNumber() == null) {
            return ResponseData.create().error("问题必须持编号").getResponse();
        }

        Option optionAdd = optionService.add(option);
        if (optionAdd == null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(optionAdd).getResponse();

    }
    @GetMapping("/query")
    @ResponseBody
    public Map<String, Object> doQuery(int questionId){
        List<Option> options = optionService.queryALlByQuestionId(questionId);
        if (options ==  null) {
            return ResponseData.create().error().getResponse();
        }
        return ResponseData.create().success().setData(options).getResponse();

    }

}
