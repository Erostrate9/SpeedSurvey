package com.yangjiao.domain;

public class Option {
    private Integer id; //选项id
    private Integer questionId; //问题id
    private String content; //选项内容
    private Integer orderNumber; //选项序号
    private Integer isAnswer; //0非答案/1答案


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", content='" + content + '\'' +
                ", orderNumber=" + orderNumber +
                ", isAnswer=" + isAnswer +
                '}';
    }
}
