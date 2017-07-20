package com.feelcolor.website.model.po;

public class UserInfo {
    private String id;

    private String name;
    
    private Integer age;
    public UserInfo(){};

    public UserInfo(String id, String name,Integer age) {
        // TODO Auto-generated constructor stub
        this.id=id;
        this.name=name;
        this.age=age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}