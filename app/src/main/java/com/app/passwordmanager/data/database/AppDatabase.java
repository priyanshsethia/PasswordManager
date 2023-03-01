package com.app.passwordmanager.data.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {SecureElement.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    public static final String DATABASE_NAME = "passwords.db";

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };


    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        synchronized (AppDatabase.class) {
            if (instance == null) {
                instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return instance;
        }
    }

    public abstract SecureElementDao secureElementDao();
}

