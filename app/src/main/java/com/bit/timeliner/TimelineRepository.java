package com.bit.timeliner;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TimelineRepository {
    private TimelineDAO timelineDAO;

    public TimelineRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        timelineDAO = database.timelineDAO();
    }

    public void insertTimeline(Timeline timeline) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            timelineDAO.insertTimeline(timeline);
        });
    }

    public void updateTimeline(Timeline timeline) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            timelineDAO.updateTimeline(timeline);
        });
    }

    public void deleteTimeline(Timeline timeline) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            timelineDAO.deleteTimeline(timeline);
        });
    }

    public LiveData<List<Timeline>> getAllTimelines() {
        return timelineDAO.getAllTimelines();
    }
}
