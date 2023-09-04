package com.bit.timeliner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;

public class TimelineDeadlinesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timeline_deadlines_fragment, container, false);
        RecyclerView timelinedDeadlinesRecycler = rootView.findViewById(R.id.deadline_timelined_recyclerview);
        timelinedDeadlinesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        String timelineName = getArguments().getString("timelineName", "Timeline Name");
        MainActivity.toolbarTitle.setText(timelineName);
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, 1);
//        MainActivity.deadlineViewModel.insertDeadline(new Deadline(1, "Science homework", calendar.getTime()));
        DeadlineTimelinedAdapter deadlineTimelinedAdapter = new DeadlineTimelinedAdapter();
        timelinedDeadlinesRecycler.setAdapter(deadlineTimelinedAdapter);
        MainActivity.deadlineViewModel.getDeadlinesForTimeline(1).observe(getViewLifecycleOwner(), new Observer<List<Deadline>>() {
            @Override
            public void onChanged(List<Deadline> deadlines) {
                deadlineTimelinedAdapter.setDeadLines(deadlines);
            }
        });
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                DeadlinesFragment deadlinesFragment = new DeadlinesFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame, deadlinesFragment)
                        .addToBackStack(null)
                        .commit();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), onBackPressedCallback);
        return rootView;
    }
}
