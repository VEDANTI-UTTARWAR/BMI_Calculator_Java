package com.example.bmicalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG="ListDataActivity";

    DatabaseHelper mDatabaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mListView=(ListView)findViewById(R.id.listView);
        mDatabaseHelper=new DatabaseHelper(this);
        populateListView();
    }

    private void populateListView() {
        Log.d(TAG,"populateListView: Displying data in the List view");
        Cursor data=mDatabaseHelper.getData();
        ArrayList<String> listData=new ArrayList<>();
        while (data.moveToNext())
        {
            listData.add(data.getString(1));

        }
        ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String bmi_result=adapterView.getItemAtPosition(i).toString();
                Log.d(TAG,"onItemClick: you Clicked on " + bmi_result);

                Cursor data=mDatabaseHelper.getItemId(bmi_result);
                int itemId=-1;
                while(data.moveToNext())
                {
                    itemId=data.getInt(0);
                }
                if (itemId>-1)
                {
                    Log.d(TAG,"onItemClick: The ID is: " + itemId);
                    Intent deleteScreenIntent=new Intent(ListDataActivity.this,DeleteDataActivity.class);
                    deleteScreenIntent.putExtra("id",itemId);
                    deleteScreenIntent.putExtra("bmi_result",bmi_result);
                    startActivity(deleteScreenIntent);

                }
                else
                {
                    toastMessage("no ID associated with the start name");
                }
            }
        });
    }
    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}