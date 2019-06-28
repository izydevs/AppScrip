package com.appscrip.amit.triviaapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appscrip.amit.triviaapp.Model.Game;
import com.appscrip.amit.triviaapp.R;

import java.util.Objects;

import static com.appscrip.amit.triviaapp.Utils.convertTimeStampToDate;

public class SummaryActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView summary;
    private Game game;
    private TextView finish;
    private TextView history;
    private boolean backTohistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.summary));

        initBindViews();
        getSummaryData();

    }

    private void getSummaryData() {
        if (getIntent() != null && getIntent().getAction().equals("game_finish")) {
            game = (Game) getIntent().getSerializableExtra("game_deatils");
            showSummaryData(game);

        } else if (getIntent() != null && getIntent().getAction().equals("history_details")) {
            game = (Game) getIntent().getSerializableExtra("game_details");
            showSummaryData(game);
            backTohistory = true;
            finish.setVisibility(View.GONE);
            history.setVisibility(View.GONE);
        }
    }

    private void initBindViews() {
        summary = findViewById(R.id.summary_tv);
        finish = findViewById(R.id.finish);
        history = findViewById(R.id.history);
        finish.setOnClickListener(this);
        history.setOnClickListener(this);
    }

    private void showSummaryData(Game game) {
        if (game != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Game Over : ").append(convertTimeStampToDate(game.getTime()) + "\n\n");
            stringBuilder.append("Name : ").append(game.getName().toUpperCase() + "\n\n");
            stringBuilder.append(getResources().getString(R.string.question_one) + "\n").append(game.getQ1Ans() + "\n\n");
            stringBuilder.append(getResources().getString(R.string.question_two) + "\n").append(game.getQ2Ans() + "\n");
            summary.setText(stringBuilder.toString());
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finish:
                startGameAgain();
                break;
            case R.id.history:
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                break;
        }
    }

    private void startGameAgain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (!backTohistory) {
            doubleClickToExit();
        } else
            super.onBackPressed();
    }

    private void doubleClickToExit() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            System.exit(0);
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
