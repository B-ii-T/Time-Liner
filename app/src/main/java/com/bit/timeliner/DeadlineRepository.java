package com.bit.timeliner;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DeadlineRepository {
    private DeadlineDAO deadlineDAO;
    private LiveData<List<Deadline>> allDeadlines;

    public DeadlineRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        deadlineDAO = database.deadlineDAO();
        allDeadlines = deadlineDAO.getAllDeadlines();
    }

    public void insertDeadline(Deadline deadline) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deadlineDAO.insertDeadline(deadline);
        });
    }

    public void updateDeadline(Deadline deadline) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deadlineDAO.updateDeadline(deadline);
        });
    }

    public void deleteDeadline(Deadline deadline) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            deadlineDAO.deleteDeadline(deadline);
        });
    }

    public LiveData<List<Deadline>> getDeadlinesForTimeline(int timelineId) {
        return deadlineDAO.getDeadlinesForTimeline(timelineId);
    }

    public LiveData<List<Deadline>> getAllDeadlines() {
        return allDeadlines;
    }
}
