package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteDataActivity extends AppCompatActivity {


    private Button btnDelete;
    private EditText deletable_item;

    DatabaseHelper mDatabaseHelper;

    private int selectedID;
    private String selectedBmi_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);

        btnDelete=(Button)findViewById(R.id.btnDelete);
        deletable_item=(EditText)findViewById(R.id.deletable_item);
        mDatabaseHelper=new DatabaseHelper(this);

        Intent receivedIntent=getIntent();
        selectedID=receivedIntent.getIntExtra("id",-1);
        selectedBmi_result=receivedIntent.getStringExtra("bmi_result");

        deletable_item.setText(selectedBmi_result);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.deleteBmi_result(selectedID,selectedBmi_result);
                deletable_item.setText("");
                toastMessage("removed from database");
            }
        });

    }

    private void toastMessage(String message)
    {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}