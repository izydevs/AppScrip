package com.appscrip.amit.triviaapp.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.appscrip.amit.triviaapp.Dao.GameDao;
import com.appscrip.amit.triviaapp.Model.Game;

@Database(entities = {Game.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final String DATABASE_NAME = "gamelist";
    private static AppDatabase instance;


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d(LOG_TAG, "Creating new database instance");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, AppDatabase.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return instance;
    }

    public abstract GameDao gameDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
