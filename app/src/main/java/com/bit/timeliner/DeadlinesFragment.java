package com.bit.timeliner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeadlinesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.all_deadlines_fragment, container, false);
        RecyclerView defaultDeadlinRecycler = rootView.findViewById(R.id.deadline_default_recyclerview);
        defaultDeadlinRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, 1);
//        deadlineViewModel.insertDeadline(new Deadline(1, "Science homework", calendar.getTime()));
        DeadlineAdapter deadlineAdapter = new DeadlineAdapter();
        defaultDeadlinRecycler.setAdapter(deadlineAdapter);
        MainActivity.deadlineViewModel.getAllDeadlines().observe(getViewLifecycleOwner(), new Observer<List<Deadline>>() {
            @Override
            public void onChanged(List<Deadline> deadlines) {
                deadlineAdapter.setDeadLines(deadlines);
            }
        });
        return rootView;
    }
}
