package com.appscrip.amit.triviaapp.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.appscrip.amit.triviaapp.Adapter.GameAdapter;
import com.appscrip.amit.triviaapp.Database.AppDatabase;
import com.appscrip.amit.triviaapp.Model.Game;
import com.appscrip.amit.triviaapp.R;
import com.appscrip.amit.triviaapp.ViewModel.GameViewModel;

import java.util.List;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GameAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private GameViewModel model;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.history_text));
        initBindViews();

    }

    private void initBindViews() {
        recyclerView = findViewById(R.id.recycler_view);
        model = ViewModelProviders.of(HistoryActivity.this).get(GameViewModel.class);
        model.getGameHistory().observe(HistoryActivity.this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                if (games != null && games.size() > 0) {
                    updateGameHistoryList(games);
                }
            }
        });
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void updateGameHistoryList(List<Game> games) {
        adapter = new GameAdapter(games, this);
        recyclerView.setAdapter(adapter);
    }


}
