package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Bmi_Cal extends AppCompatActivity {
    Button btncalculate;
    private EditText height;
    private  EditText weight;

    private Object View;
    RadioButton male,female;
    RadioGroup gender;
    private EditText getAge,getName;
    private Object v;
    float bmi=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__cal);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);
        getAge=(EditText)findViewById(R.id.getAge);
        getName=(EditText)findViewById(R.id.getName);
        weight=(EditText)findViewById(R.id.weight);
        height=(EditText)findViewById(R.id.height);
        btncalculate=(Button)findViewById(R.id.btncalculate);

        btncalculate.setOnClickListener(new android.view.View.OnClickListener()
        {

            @Override
            public void onClick(android.view.View v) {
                Intent i=new Intent(Bmi_Cal.this,Result.class);
                String heightStr=height.getText().toString();
                String weightStr=weight.getText().toString();
                if(heightStr!=null && !"".equals(heightStr) && weightStr!=null && !"".equals(weightStr))
                {
                    float heightValue=Float.parseFloat(heightStr)/100;
                    float weightValue=Float.parseFloat(weightStr);
                    float bmi=weightValue/(heightValue*heightValue);
                    i.putExtra("your bmi",bmi);
                    int age=Integer.parseInt(getAge.getText().toString());
                    String name=getName.getText().toString();
                    i.putExtra("name",name);
                    i.putExtra("Age",age);
                    i.putExtra("height",heightValue);
                    i.putExtra("weight",weightValue);
                    startActivity(i);
                }
                //i.putExtra("your bmi ",bmi);
                //  startActivity(i);
            }
        });
    }
    }
