package com.yangjiao.domain;

public class IpRestrict {
    private Integer id;
    private int questionnaireId; //所填问卷id
    private int ip; //填写者ip

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaire_id(int questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }
}
