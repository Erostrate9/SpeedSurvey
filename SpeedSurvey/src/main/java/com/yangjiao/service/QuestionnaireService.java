package com.yangjiao.service;

import com.yangjiao.domain.Questionnaire;

import java.util.List;

public interface QuestionnaireService {
    Questionnaire create(Questionnaire questionnaire);
    void updateState();
    Questionnaire endByID(int id);
    Questionnaire queryById(int id);
    Questionnaire queryByUrl(String url);
    List<Questionnaire> queryALlByPublisherId(int publisherId);
    List<Questionnaire> queryAllInPublic();
}
