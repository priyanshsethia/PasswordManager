package com.app.passwordmanager.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SecureElementDao {

    /**
     * Insert Data
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(SecureElement secureElement);

    /**
     * Fetch Data
     */
    @Query("SELECT * FROM secureElement")
    LiveData<List> getAllPasscodes();

    /**
     * Update Data
     */
    @Update
    void update(SecureElement secureElement);

    /**
     * Delete Data
     */
    @Delete
    void delete(SecureElement secureElement);
}
