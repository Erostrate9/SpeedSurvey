package com.yangjiao.service;

import com.yangjiao.domain.Question;
import com.yangjiao.domain.Questionnaire;

import java.util.List;

public interface QuestionService {
    Question add(Question question);
    List<Question> queryALlByQuestionnaireId(int questionnaireId);
}
