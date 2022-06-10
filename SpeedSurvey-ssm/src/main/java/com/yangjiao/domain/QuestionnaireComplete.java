package com.yangjiao.domain;

import java.util.Date;
import java.util.List;

public class QuestionnaireComplete {
    private Questionnaire questionnaire;
    private List<QuestionComplete> questionCompletes;

    public QuestionnaireComplete() {

    }

    public QuestionnaireComplete(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;

    }

    public QuestionnaireComplete(Questionnaire questionnaire, List<QuestionComplete> questionCompletes) {
        this.questionnaire = questionnaire;
        this.questionCompletes = questionCompletes;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<QuestionComplete> getQuestionCompletes() {
        return questionCompletes;
    }

    public void setQuestionCompletes(List<QuestionComplete> questionCompletes) {
        this.questionCompletes = questionCompletes;
    }
}
