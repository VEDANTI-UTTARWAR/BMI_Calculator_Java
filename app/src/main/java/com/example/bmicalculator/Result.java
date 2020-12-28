package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private TextView result;
    Button ok,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result=(TextView)findViewById(R.id.result);
        save=(Button)findViewById(R.id.save);
        mDatabaseHelper=new DatabaseHelper(this);
        ok=(Button)findViewById(R.id.ok) ;
        Intent in=getIntent();

        float bmi=in.getFloatExtra("your bmi",0);
        float weightValue=in.getFloatExtra("weight",0);
        float heightValue=in.getFloatExtra("height",0);
        int age=in.getIntExtra("Age",0);
        String name=in.getStringExtra("name");

        //result.setText("bmi is "+bmi);
        String bmiLabel="";
        if(Float.compare(bmi,15f)<=0)
        {
            bmiLabel=getString(R.string.very_severely_underweight);
        }
        else if(Float.compare(bmi,15f)>0 && Float.compare(bmi,16f)<=0)
        {
            bmiLabel=getString(R.string.severely_underweight);
        }
        else if(Float.compare(bmi,16f)>0 && Float.compare(bmi,18.5f)<=0)
        {
            bmiLabel=getString(R.string.underweight);
        }
        else if(Float.compare(bmi,18.5f)>0 && Float.compare(bmi,25f)<=0)
        {
            bmiLabel=getString(R.string.normal);
        }
        else if(Float.compare(bmi,25f)>0 && Float.compare(bmi,30f)<=0)
        {
            bmiLabel=getString(R.string.overweight);
        }
        else if(Float.compare(bmi,30f)>0 && Float.compare(bmi,35f)<=0)
        {
            bmiLabel=getString(R.string.obese_class_i);
        }
        else if(Float.compare(bmi,35f)>0 && Float.compare(bmi,40f)<=0)
        {
            bmiLabel=getString(R.string.obese_class_ii);
        }
        else {
            bmiLabel=getString(R.string.obese_class_iii);
        }
        //result.setText("bmi is "+bmi);
        // bmiLabel=bmi+"\n"+age+"\n"+weightValue+"\n"+heightValue+"\n"+bmiLabel;
        bmiLabel="Name: "+name+"\n"+"Age: "+age+"\n"+"weight: "+weightValue+"\n"+"height: "+heightValue+"\n"+"BMI is: "+bmi+"\n"+bmiLabel;
        result.setText(bmiLabel);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Result.this,ListDataActivity.class);


                String newEntry=result.getText().toString();
                if(result.length()!=0)
                {
                    AddData(newEntry);
                    result.setText(" ");
                    startActivity(intent);
                }
                else
                {
                    toastMessage("you must put something in the next field");
                }

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i=new Intent(Result.this,Bmi_Chart.class);
                startActivity(i);
            }
        });
    }
    public void AddData(String newEntry)
    {

        boolean insertData=mDatabaseHelper.addData(newEntry);
        if(insertData)
        {
            toastMessage("data inserted successfully");
        }
        else
        {
            toastMessage("something went wrong");
        }
    }

    private void toastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
