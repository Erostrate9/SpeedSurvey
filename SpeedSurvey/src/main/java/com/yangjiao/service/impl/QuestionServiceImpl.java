package com.yangjiao.service.impl;

import com.yangjiao.domain.Question;
import com.yangjiao.mapper.QuestionMapper;
import com.yangjiao.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Question add(Question question) {
        questionMapper.insert(question);
        return questionMapper.selectById(question.getId());
    }

    @Override
    public List<Question> queryALlByQuestionnaireId(int questionnaireId) {
        return questionMapper.selectAllByQuestionnaireId(questionnaireId);
    }
}
