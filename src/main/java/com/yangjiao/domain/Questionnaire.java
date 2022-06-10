package com.yangjiao.domain;

import java.util.Date;

public class Questionnaire {
    public static final int STATE_CREATE = 0;
    public static final int STATE_EXECUTE = 1;
    public static final int STATE_END = 2;


    private Integer id; //问卷id
    private Integer userId; //发布者id
    private String title; //标题
    private String description; //描述
    private Integer passwordRequired; //0公开/1密码访问
    private String password; //访问密码
    private Integer ipLimit; //0不限制/1限制ip
    private Integer belongOrganization; //0普通问卷/组织id
    private String url; //问卷url
    private Integer state; //0已创建/1进行中/2已结束
    private Date createTime; //创建时间
    private Date startTime; //开始时间
    private Date endTime; //结束时间

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPasswordRequired() {
        return passwordRequired;
    }

    public void setPasswordRequired(Integer passwordRequired) {
        this.passwordRequired = passwordRequired;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIpLimit() {
        return ipLimit;
    }

    public void setIpLimit(int ipLimit) {
        this.ipLimit = ipLimit;
    }

    public Integer getBelongOrganization() {
        return belongOrganization;
    }

    public void setBelongOrganization(int belongOrganization) {
        this.belongOrganization = belongOrganization;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
