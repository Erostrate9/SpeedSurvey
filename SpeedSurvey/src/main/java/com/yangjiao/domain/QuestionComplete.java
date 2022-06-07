package com.yangjiao.domain;

import java.util.Date;
import java.util.List;

public class QuestionComplete {
    private Question question;
    private List<Option> options; //包含选项

    public QuestionComplete() {

    }

    public QuestionComplete(Question question) {
        this.question  = question;
    }
    public QuestionComplete(Question question,List<Option> options) {
        this.question  = question;
        this.options = options;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
