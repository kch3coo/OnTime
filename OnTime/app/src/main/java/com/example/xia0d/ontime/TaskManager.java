package com.example.xia0d.ontime;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TaskManager implements Serializable{

    private Map<String, Task> allTasksMap;

     TaskManager(){
        allTasksMap = new HashMap<>();

    }

    public void setAllTasksMap(Map<String, Task> allTasksMap) {
        this.allTasksMap = allTasksMap;
    }

    public void appendNewTask(String task){
         if(allTasksMap != null) {
             allTasksMap.put(task, createNewTask(task));
         }
    }

    private Task createNewTask(String task){return new Task(task, false);}

    public void deleteTask(String task){
        allTasksMap.remove(task);
    }


    public Map<String, Task> getallTaskMap(){
        return allTasksMap;
    }

}
