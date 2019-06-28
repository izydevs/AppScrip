package com.appscrip.amit.triviaapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.appscrip.amit.triviaapp.R;

import org.w3c.dom.Text;

public class QuestionOne extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView next;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_one);

        initBindViews();
    }

    private void initBindViews() {
        if (getIntent()!=null && !getIntent().getStringExtra("name").equals(""))
            name= getIntent().getStringExtra("name");

        radioGroup = findViewById(R.id.radio_group);
        next = findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValid();
            }
        });
    }

    private void isValid() {
        int isSelect = radioGroup.getCheckedRadioButtonId();
        if (isSelect ==-1){
            Toast.makeText(getApplicationContext(),"Select answer.!!",Toast.LENGTH_LONG).show();
        }else {
            setNextQuestion(isSelect);
        }
    }

    private void setNextQuestion(int radioBtnId) {
        RadioButton radioButton = findViewById(radioBtnId) ;
        Intent intent = new Intent(this,QuestionTwo.class);
        intent.putExtra("name",name);
        intent.putExtra("ques_one",radioButton.getText().toString());
        startActivity(intent);
    }
}
