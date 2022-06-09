package com.yangjiao.service.impl;

import com.yangjiao.domain.TextResult;
import com.yangjiao.mapper.TextResultMapper;
import com.yangjiao.service.TextResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextResultServiceImpl implements TextResultService {
    @Autowired
    TextResultMapper textResultMapper;

    @Override
    public TextResult add(TextResult textResult) {
        textResultMapper.insert(textResult);
        return textResultMapper.selectById(textResult.getId());
    }

    @Override
    public List<TextResult> queryAllByQuestionId(int questionId, int startIndex, int pageSize) {
        return textResultMapper.selectAllByQuestionId(questionId, startIndex, pageSize);
    }
}
