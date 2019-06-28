package com.appscrip.amit.triviaapp.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.appscrip.amit.triviaapp.Dao.GameDao;
import com.appscrip.amit.triviaapp.Database.AppDatabase;
import com.appscrip.amit.triviaapp.Model.Game;

import java.util.List;

public class GameRepository {
    private GameDao gameDao;
    private LiveData<List<Game>> myGameList;

    public GameRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        gameDao = appDatabase.gameDao();
        myGameList = gameDao.getGameHistoryList();
    }

    public LiveData<List<Game>> getMyGameList(){
      return myGameList;
    }

    public void insertGameHistory(Game game){
        new insertAsyncTask(gameDao).execute(game);
    }

    private class insertAsyncTask extends AsyncTask<Game,Void,Void> {
        private GameDao gameDao;
        public insertAsyncTask(GameDao gameDao) {
            this.gameDao = gameDao;        }

        @Override
        protected Void doInBackground(Game... games) {
            gameDao.insertGame(games[0]);
            return null;
        }
    }
}
