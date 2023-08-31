package com.bit.timeliner;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TimelineViewModel extends AndroidViewModel {
    private TimelineRepository repository;

    public TimelineViewModel(Application application) {
        super(application);
        repository = new TimelineRepository(application);
    }

    public void insertTimeline(Timeline timeline) {
        repository.insertTimeline(timeline);
    }

    public void updateTimeline(Timeline timeline) {
        repository.updateTimeline(timeline);
    }

    public void deleteTimeline(Timeline timeline) {
        repository.deleteTimeline(timeline);
    }

    public LiveData<List<Timeline>> getAllTimelines() {
        return repository.getAllTimelines();
    }
}
