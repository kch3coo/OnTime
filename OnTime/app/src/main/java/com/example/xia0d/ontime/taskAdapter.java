package com.example.xia0d.ontime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class taskAdapter extends ArrayAdapter<Task>{

    private Task[] tasksList;
    private Context context;

     taskAdapter(Task[] tasksList, Context context){
        super(context, R.layout.task_adapter, tasksList);
        this.tasksList = tasksList;
        this.context = context;
    }

    private static class taskHolder{
         TextView taskName;
         CheckBox chkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;

        taskHolder holder = new taskHolder();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.task_adapter, null);

            holder.taskName = (TextView) v.findViewById(R.id.taskTextView);
            holder.chkBox = (CheckBox) v.findViewById(R.id.taskCheckBox);

            holder.chkBox.setOnCheckedChangeListener((TasksActivity) context);

        } else {
            holder = (taskHolder) v.getTag();
        }

        Task task = tasksList[position];
        holder.taskName.setText(task.getTaskName());
        holder.chkBox.setChecked(task.getCompletion());
        holder.chkBox.setTag(task);

        return v;
    }
}
