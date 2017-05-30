package com.example.amank.edit;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ashutosh on 5/30/2017.
 */

public class Testlist extends AppCompatActivity {

    static  Testlist tl;
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
        Log.i("hello","adaptive");
        Cursor res = MainActivity.activityA.myDB.getALLData();
        task.clear();
        while (res.moveToNext()){
            int id =  res.getInt(0);
            String name = res.getString(1);
            String amount = res.getString(2);
            byte[] photos = res.getBlob(3);
            Log.i("hello","while");

            task.add(new TaskWall(id, name, amount, photos));
        }


        adapter.notifyDataSetChanged();
        tl = this;
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long ide) {
               String namer = task.get(position).getName();
                MainActivity.activityA.myDB.deleteEntry(namer);
                Cursor res = MainActivity.activityA.myDB.getALLData();
                task.clear();
                while (res.moveToNext()){
                    int id =  res.getInt(0);
                    String name = res.getString(1);
                    String amount = res.getString(2);
                    byte[] photos = res.getBlob(3);
                    Log.i("hello","pop");

                    task.add(new TaskWall(id, name, amount, photos));
                }
                adapter.notifyDataSetChanged();

            }
        });





    }
    public void cow(View v ){
        final int postion = lw.getPositionForView(v);

        String namer = task.get(postion).getName();
        MainActivity.activityA.myDB.deleteEntry(namer);
        Cursor res = MainActivity.activityA.myDB.getALLData();
        task.clear();
        while (res.moveToNext()) {
            int id = res.getInt(0);
            String name = res.getString(1);
            String amount = res.getString(2);
            byte[] photos = res.getBlob(3);
            Log.i("hello", "pop");

            task.add(new TaskWall(id, name, amount, photos));
        }
        adapter.notifyDataSetChanged();


    }


}
