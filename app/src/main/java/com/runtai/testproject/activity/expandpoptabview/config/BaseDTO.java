package com.runtai.testproject.activity.expandpoptabview.config;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseDTO implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
