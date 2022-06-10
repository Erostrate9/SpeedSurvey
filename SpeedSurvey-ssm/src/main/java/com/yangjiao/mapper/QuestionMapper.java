package com.yangjiao.mapper;

import com.yangjiao.domain.Question;

import java.util.List;

public interface QuestionMapper {
    int insert(Question question);
    List<Question> selectAllByQuestionnaireId(int questionnaireId);
    Question selectById(int id);
    int deleteByIdInt(int id);
    int update(Question question);
}
