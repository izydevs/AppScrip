package com.appscrip.amit.triviaapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.appscrip.amit.triviaapp.Model.Game;
import com.appscrip.amit.triviaapp.Repository.GameRepository;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private LiveData<List<Game>> myList;
    private GameRepository repository;

    public GameViewModel(Application application) {
        super(application);
        repository = new GameRepository(application);
        myList = repository.getMyGameList();

    }

    public LiveData<List<Game>> getGameHistory() {
        if (myList == null) {
            myList = new MutableLiveData<>();
        }
        return myList;
    }

    public void insertData(Game game) {
        repository.insertGameHistory(game);
    }
}
