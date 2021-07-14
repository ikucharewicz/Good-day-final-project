package com.example.goodday.service.dto;

import com.example.goodday.models.User;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;

public class DailyTaskDto {

    @NotNull
    @NotEmpty
    private int id;

    @NotNull
    @NotEmpty
    private User user;

    private String task;
    private String success;

    @NotNull
    @NotEmpty
    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
