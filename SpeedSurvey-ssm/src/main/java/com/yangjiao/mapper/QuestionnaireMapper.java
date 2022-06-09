package com.yangjiao.mapper;

import com.yangjiao.domain.Questionnaire;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionnaireMapper {

    Questionnaire selectById(int id);
    Questionnaire selectByUrl(String url);

    List<Questionnaire> selectAllByUserId(int publisherId);
    List<Questionnaire> selectAllInPublic();
    int insert(Questionnaire questionnaire);
    List<Questionnaire> selectAllToBeStart();
    List<Questionnaire> selectAllToBeEnd();
    int updateStateById(@Param("id") int id, @Param("state") int state);
    int deleteById(int id);
    int update(Questionnaire questionnaire);
}
