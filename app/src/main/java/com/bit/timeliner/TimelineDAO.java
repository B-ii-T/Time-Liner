package com.bit.timeliner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface TimelineDAO {
    @Insert
    void insertTimeline(Timeline timeline);

    @Update
    void updateTimeline(Timeline timeline);

    @Delete
    void deleteTimeline(Timeline timeline);

    @Query("SELECT * FROM timeline_table")
    LiveData<List<Timeline>> getAllTimelines();
}
