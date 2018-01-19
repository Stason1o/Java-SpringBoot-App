package com.sbogdanschi.springboot.model;

import com.sbogdanschi.springboot.entity.User;

import java.util.List;

public class AjaxResponseBody {

    private String msg;
    private List<User> retrievedUsers;
    private Object obj;

    public AjaxResponseBody() {
        //empty constructor
    }

    public AjaxResponseBody(String msg, Object obj) {
        this.msg = msg;
        this.obj = obj;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<User> getRetrievedUsers() {
        return retrievedUsers;
    }

    public void setRetrievedUsers(List<User> retrievedUsers) {
        this.retrievedUsers = retrievedUsers;
    }
}
