package com.example.xia0d.ontime;

import java.io.Serializable;

class Task implements Serializable{
    private String taskName;
    private boolean completed;
    Task(String task, boolean completion){
        this.taskName = task;
        this.completed = completion;
    }

    public boolean getCompletion(){return completed;}

    public void setCompletion(boolean isFinished){
        completed = isFinished;
    }

    public String getTaskName(){return taskName;}
}
