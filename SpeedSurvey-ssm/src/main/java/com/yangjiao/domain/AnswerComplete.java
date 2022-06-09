package com.yangjiao.domain;

import java.util.List;

public class AnswerComplete {
    private Integer userId;
    private List<Answer> answers;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "AnswerComplete{" +
                "userId=" + userId +
                ", answers=" + answers +
                '}';
    }
}
