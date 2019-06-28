package com.appscrip.amit.triviaapp.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appscrip.amit.triviaapp.Model.Game;
import com.appscrip.amit.triviaapp.R;
import com.appscrip.amit.triviaapp.ViewModel.GameViewModel;

import java.io.Serializable;

public class QuestionTwo extends AppCompatActivity {

    private String name;
    private String ques1Ans;
    private TextView next;
    private CheckBox option1;
    private CheckBox option2;
    private CheckBox option3;
    private CheckBox option4;
    StringBuilder options = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_two);

        if (getIntent() != null && !getIntent().getStringExtra("name").equals("") && !getIntent().getStringExtra("ques_one").equals("")) {
            name = getIntent().getStringExtra("name");
            ques1Ans = getIntent().getStringExtra("ques_one");
        }
        initBindViews();
    }

    private void initBindViews() {
        next = findViewById(R.id.next_btn);
        option1 = findViewById(R.id.option_one_cb);
        option2 = findViewById(R.id.option_two_cb);
        option3 = findViewById(R.id.option_three_cb);
        option4 = findViewById(R.id.option_four_cb);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isValid();
            }
        });
    }

    private void isValid() {

        if (!option1.isChecked() && !option2.isChecked() && !option3.isChecked() && !option4.isChecked()) {
            Toast.makeText(getApplicationContext(), "Select answer.!!", Toast.LENGTH_LONG).show();
        } else {
           checkOptions(option1);
           checkOptions(option2);
           checkOptions(option3);
           checkOptions(option4);
           options.deleteCharAt(options.length()-1);
           saveDataAndShowSummary(new Game(System.currentTimeMillis(),name,ques1Ans,options.toString()));
        }
    }

    private void checkOptions(CheckBox option1) {
        if(option1.isChecked())
            options.append(option1.getText().toString()).append(",");
    }

    private void saveDataAndShowSummary(Game game) {
        GameViewModel model = ViewModelProviders.of(this).get(GameViewModel.class);
        model.insertData(game);

        Intent intent = new Intent(this, SummaryActivity.class);
        intent.setAction("game_finish");
        intent.putExtra("game_deatils",(Serializable)game);
        startActivity(intent);
    }
}
