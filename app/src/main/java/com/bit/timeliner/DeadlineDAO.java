package com.bit.timeliner;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DeadlineDAO {
    @Insert
    void insertDeadline(Deadline deadline);

    @Update
    void updateDeadline(Deadline deadline);

    @Delete
    void deleteDeadline(Deadline deadline);

    @Query("SELECT * FROM deadline_table WHERE timelineId = :timelineId")
    LiveData<List<Deadline>> getDeadlinesForTimeline(int timelineId);
}
