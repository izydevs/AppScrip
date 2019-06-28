package com.appscrip.amit.triviaapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appscrip.amit.triviaapp.R;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBindViews();
    }

    private void initBindViews() {
        name = findViewById(R.id.name_et);
        next = findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValid();
            }
        });
    }

    private void isValid() {
        if (name.getText().toString()!=null && !name.getText().toString().equals("")){
            setNextQuestion();
        }else {
            name.setError("Enter name.!!");
        }
    }

    private void setNextQuestion() {
        Intent intent = new Intent(this,QuestionOne.class);
        intent.putExtra("name",name.getText().toString());
        startActivity(intent);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
