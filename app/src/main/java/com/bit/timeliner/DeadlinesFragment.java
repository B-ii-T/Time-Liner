package com.bit.timeliner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeadlinesFragment extends Fragment {
    private long lastBackPressedTime = 0;
    private static final long DOUBLE_TAP_EXIT_INTERVAL = 2000; // 2 seconds
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
        MainActivity.toolbarTitle.setText("Deadlines");
        MainActivity.deadlineViewModel.getAllDeadlines().observe(getViewLifecycleOwner(), new Observer<List<Deadline>>() {
            @Override
            public void onChanged(List<Deadline> deadlines) {
                deadlineAdapter.setDeadLines(deadlines);
            }
        });
        OnBackPressedCallback doubleTapExitCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                long currentTime = System.currentTimeMillis();
                if (currentTime - lastBackPressedTime < DOUBLE_TAP_EXIT_INTERVAL) {
                    requireActivity().finish(); // Exit the app
                } else {
                    lastBackPressedTime = currentTime;
                    Toast.makeText(requireContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getActivity(), doubleTapExitCallback);
        return rootView;
    }
}
