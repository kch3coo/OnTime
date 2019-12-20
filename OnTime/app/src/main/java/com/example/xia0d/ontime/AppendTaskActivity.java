package com.example.xia0d.ontime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AppendTaskActivity extends AppCompatActivity {

    private EditText editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_append_task);
        editTask = (EditText)findViewById(R.id.taskEdit);
        goToTaskBoard();
        addTask();
    }

    /**
     * Activate button to go to score board activity.
     */
    private void goToTaskBoard(){
       Button taskBoard = findViewById(R.id.TaskViewPage);
        taskBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreBoard = new Intent(AppendTaskActivity.this,
                        TasksActivity.class);
                startActivity(scoreBoard);
                overridePendingTransition(R.anim.slide_inleft, R.anim.slide_outright);
            }
        });
    }

    /**
     * Activate button to go to score board activity.
     */
    private void addTask(){
        Button addTask = findViewById(R.id.appendTasks);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTask.getText().toString();
                TaskManager taskManager = (TaskManager) FileSaver.loadFromFile(getApplicationContext(), FileSaver.fileName);

                if(taskManager != null){taskManager.appendNewTask(task);}
                else{taskManager = new TaskManager();}

                FileSaver.saveToFile(getApplicationContext(), taskManager, FileSaver.fileName);

                Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_LONG).show();
            }
        });
    }




}

