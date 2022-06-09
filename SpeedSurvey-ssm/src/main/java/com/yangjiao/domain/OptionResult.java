package com.yangjiao.domain;

import java.util.Date;

public class OptionResult {
    private Integer id; //选择题答题id
    private Integer userId; //填写者id
    private Integer optionId; //选项id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }


}
