package com.appscrip.amit.triviaapp.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.appscrip.amit.triviaapp.Model.Game;

import java.util.List;

@Dao
public interface GameDao {

    @Query("SELECT * FROM game")
    LiveData<List<Game>> getGameHistoryList();

    @Insert
    void insertGame(Game game);
}
