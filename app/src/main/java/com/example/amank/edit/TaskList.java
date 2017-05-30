package com.example.amank.edit;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.jar.Manifest;

/**
 * Created by Ashutosh on 5/30/2017.
 */

class TaskList extends AppCompatActivity{

    ListView lw;
    ArrayList<TaskWall> task;
    TaskListAdapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list_activity);

        lw = (ListView)findViewById(R.id.list_view);
        task = new ArrayList<>();
        adapter = new TaskListAdapter(this, R.layout.taski_items,task);

        lw.setAdapter(adapter);


        //get All data from database


        Cursor res = MainActivity.getInstance().myDB.getALLData();
        task.clear();
        while (res.moveToNext()){
            int id =  res.getInt(0);
            String name = res.getString(1);
            String amount = res.getString(2);
            byte[] photos = res.getBlob(3);

            task.add(new TaskWall(id, name, amount, photos));
        }
        adapter.notifyDataSetChanged();


    }
}
