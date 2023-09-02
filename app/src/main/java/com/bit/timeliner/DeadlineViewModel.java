package com.bit.timeliner;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DeadlineViewModel extends AndroidViewModel {
    private DeadlineRepository repository;
    private LiveData<List<Deadline>> allDeadlines;

    public DeadlineViewModel(Application application) {
        super(application);
        repository = new DeadlineRepository(application);
        allDeadlines = repository.getAllDeadlines();
    }

    public void insertDeadline(Deadline deadline) {
        repository.insertDeadline(deadline);
    }

    public void updateDeadline(Deadline deadline) {
        repository.updateDeadline(deadline);
    }

    public void deleteDeadline(Deadline deadline) {
        repository.deleteDeadline(deadline);
    }

    public LiveData<List<Deadline>> getDeadlinesForTimeline(int timelineId) {
        return repository.getDeadlinesForTimeline(timelineId);
    }

    public LiveData<List<Deadline>> getAllDeadlines() {
        return allDeadlines;
    }
}
