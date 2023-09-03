package com.bit.timeliner;

import androidx.appcompat.app.AppCompatActivity;
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
//        RecyclerView defaultDeadlinRecycler = findViewById(R.id.deadline_default_recyclerview);
//        defaultDeadlinRecycler.setLayoutManager(new LinearLayoutManager(this));
//        deadlineViewModel = new ViewModelProvider(this).get(DeadlineViewModel.class);
//        timelineViewModel = new ViewModelProvider(this).get(TimelineViewModel.class);
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, 1);
//        deadlineViewModel.insertDeadline(new Deadline(1, "Science homework", calendar.getTime()));
//        DeadlineAdapter adapter = new DeadlineAdapter();
//        defaultDeadlinRecycler.setAdapter(adapter);
//        deadlineViewModel.getAllDeadlines().observe(this, new Observer<List<Deadline>>() {
//            @Override
//            public void onChanged(List<Deadline> deadlines) {
//                adapter.setDeadLines(deadlines);
//            }
//        });
    }
}