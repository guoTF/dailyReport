package com.seda.dailyReport.model;

import java.io.Serializable;

public class Post implements Serializable {
    private Integer id;

    private String name;

    private Integer depName;

    private static final long serialVersionUID = 1L;

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

    public Integer getDepName() {
        return depName;
    }

    public void setDepName(Integer depName) {
        this.depName = depName;
    }
}