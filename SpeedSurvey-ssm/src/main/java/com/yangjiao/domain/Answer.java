package com.yangjiao.domain;

import java.util.Arrays;

public class Answer {
    private Integer questionId; //问题id
    private Integer type; //题目类型
    private String text; //填空题回答
    private Integer optionId; //单选题回答
    private Integer[] optionIds; //多选题回答



    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer[] getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(Integer[] optionIds) {
        this.optionIds = optionIds;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "questionId=" + questionId +
                ", type=" + type +
                ", text='" + text + '\'' +
                ", optionId=" + optionId +
                ", optionIds=" + Arrays.toString(optionIds) +
                '}';
    }
}
