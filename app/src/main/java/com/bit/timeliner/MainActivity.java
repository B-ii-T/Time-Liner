package com.bit.timeliner;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static DeadlineViewModel deadlineViewModel;
    public static TimelineViewModel timelineViewModel;
    public static Toolbar toolbar;
    public static TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Deadlines");
        deadlineViewModel = new ViewModelProvider(this).get(DeadlineViewModel.class);
        timelineViewModel = new ViewModelProvider(this).get(TimelineViewModel.class);
        RecyclerView timelinesRecyclerView = findViewById(R.id.timelines_recycler_view);
        timelinesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        TimelineAdapter timelineAdapter = new TimelineAdapter();
        timelinesRecyclerView.setAdapter(timelineAdapter);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationIcon(R.drawable.hmbrgr_icon);
        toolbar.setNavigationOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
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