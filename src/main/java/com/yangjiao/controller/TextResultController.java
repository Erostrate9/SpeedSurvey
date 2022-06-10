package com.yangjiao.controller;

import com.yangjiao.domain.Questionnaire;
import com.yangjiao.domain.TextResult;
import com.yangjiao.domain.User;
import com.yangjiao.service.TextResultService;
import com.yangjiao.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/text_result")
public class TextResultController {
    @Autowired
    private TextResultService textResultService;

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam("questionId") int questionId, @RequestParam("currentPage") int currentPage, @RequestParam("pageSize") int pageSize, HttpSession httpSession) {

        List<TextResult> textResults = textResultService.queryAllByQuestionId(questionId,(currentPage-1)*pageSize,pageSize);
        if (textResults == null) {
            return ResponseData.create().error().getResponse();
        }
        HashMap<String, Object> textResultMap = new HashMap<>();
        List<Map<String, Object>> details = new ArrayList<>();
        for (TextResult textResult : textResults) {
            HashMap<String, Object> detail = new HashMap<>();
            detail.put("context", textResult.getAnswer());
            details.add(detail);
        }
        textResultMap.put("detail", details);
        textResultMap.put("total", details.size());
        return ResponseData.create().success().setData(textResultMap).getResponse();
    }
}
