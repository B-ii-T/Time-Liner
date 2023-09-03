package com.bit.timeliner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static DeadlineViewModel deadlineViewModel;
    public static TimelineViewModel timelineViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        deadlineViewModel = new ViewModelProvider(this).get(DeadlineViewModel.class);
        timelineViewModel = new ViewModelProvider(this).get(TimelineViewModel.class);
        RecyclerView timelinesRecyclerView = findViewById(R.id.timelines_recycler_view);
        timelinesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TimelineAdapter timelineAdapter = new TimelineAdapter();
        timelinesRecyclerView.setAdapter(timelineAdapter);
        timelineViewModel.getAllTimelines().observe(this, new Observer<List<Timeline>>() {
            @Override
            public void onChanged(List<Timeline> timelines) {
                timelineAdapter.setTimelines(timelines);
            }
        });
        DeadlinesFragment deadlinesFragment = new DeadlinesFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, deadlinesFragment)
                .commit();

        timelineAdapter.setOnTimelineClickListener((timelineId, timelineName) -> {
            Bundle args = new Bundle();
            args.putInt("timelineId", timelineId);
            args.putString("timelineName", timelineName);
            TimelineDeadlinesFragment timelineDeadlinesFragment = new TimelineDeadlinesFragment();
            timelineDeadlinesFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, timelineDeadlinesFragment).commit();
            drawerLayout.close();
        });
    }
}