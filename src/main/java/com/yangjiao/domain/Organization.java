package com.yangjiao.domain;

public class Organization {
    private Integer id; //组织id
    private String name; //组织名称
    private String description; //组织描述
    private String logo; //组织标志
    private Integer ownerId; //组织所有者id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
