package com.example.todo;

public class Todo {
    private long id;
    private String task;

    public void setId(long id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public long getId() {
        return id;
    }

    public String getTask() {
        return task;
    }
}