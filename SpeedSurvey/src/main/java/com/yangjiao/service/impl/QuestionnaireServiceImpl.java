package com.yangjiao.service.impl;

import com.yangjiao.domain.Questionnaire;
import com.yangjiao.mapper.QuestionnaireMapper;
import com.yangjiao.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    @Override
    public Questionnaire create(Questionnaire questionnaire) {
        questionnaireMapper.insert(questionnaire);
        return questionnaireMapper.selectById(questionnaire.getId());
    }

    @Override
    public void updateState() {
        List<Questionnaire> questionnairesToBeStart = questionnaireMapper.selectAllToBeStart();
        //创建状态转变为开始状态
        for (Questionnaire questionnaireToBeStart : questionnairesToBeStart) {
            questionnaireMapper.updateStateById(questionnaireToBeStart.getId(), Questionnaire.STATE_EXECUTE);
        }
        //开始状态转变为结束状态
        List<Questionnaire> questionnairesToBeEnd = questionnaireMapper.selectAllToBeEnd();
        for (Questionnaire questionnaireToBeEnd : questionnairesToBeEnd) {
            questionnaireMapper.updateStateById(questionnaireToBeEnd.getId(), Questionnaire.STATE_END);
        }
    }

    @Override
    public Questionnaire endByID(int id) {
        questionnaireMapper.updateStateById(id, Questionnaire.STATE_END);
        return questionnaireMapper.selectById(id);
    }

    @Override
    public Questionnaire queryById(int id) {
        return questionnaireMapper.selectById(id);
    }

    @Override
    public List<Questionnaire> queryALlByPublisherId(int publisherId) {
        return questionnaireMapper.selectAllByUserId(publisherId);
    }

    @Override
    public List<Questionnaire> queryAllInPublic() {
        return questionnaireMapper.selectAllInPublic();
    }

    @Override
    public Questionnaire queryByUrl(String url) {
        return questionnaireMapper.selectByUrl(url);
    }
}
