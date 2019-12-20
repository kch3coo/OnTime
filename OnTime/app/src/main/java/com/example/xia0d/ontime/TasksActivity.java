package com.example.xia0d.ontime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class TasksActivity extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener{

    ListView lv;
    Task[] taskList;
    ArrayAdapter taskAdapter;
    TaskManager tm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        loadTaskList();
        onSyncButtonClick();
        backToAddTaskButton();

        lv = (ListView) findViewById(R.id.taskListView);
        displayTasks();
    }
    private void displayTasks(){
        taskList = tm.getallTaskMap().values().toArray(new Task[tm.getallTaskMap().size()]);
        taskAdapter = new taskAdapter(taskList, this);
        ListView listView = (ListView) findViewById(R.id.taskListView);
        listView.setAdapter(taskAdapter);
    }
    private void loadTaskList(){
        tm = (TaskManager)FileSaver.loadFromFile(this, FileSaver.fileName);
        if(tm == null){tm = new TaskManager();}
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int pos = lv.getPositionForView(buttonView);
        if(pos != ListView.INVALID_POSITION){
            Task t = taskList[pos];
            tm.deleteTask(t.getTaskName());
            FileSaver.saveToFile(this, tm, FileSaver.fileName);
            Toast.makeText(this, "Task Deleted", Toast.LENGTH_LONG).show();
        }
    }

    private void onSyncButtonClick(){
        ImageButton syncButton = findViewById(R.id.refreshTaskButton);
        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent refresh = new Intent(TasksActivity.this,
                        TasksActivity.class);
                startActivity(refresh);
                overridePendingTransition(R.anim.slide_inright, R.anim.slide_outleft);
            }
        });
    }

    private void backToAddTaskButton(){
        Button backToAddTaskButton = findViewById(R.id.backToAddTask);
        backToAddTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(TasksActivity.this,
                        AppendTaskActivity.class);
                startActivity(addTask);
                overridePendingTransition(R.anim.slide_inright, R.anim.slide_outleft);
            }
        });
    }
}
