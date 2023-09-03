package com.bit.timeliner;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeadlineAdapter extends RecyclerView.Adapter<DeadlineAdapter.ViewHolder> {
    private List<Deadline> deadlines = new ArrayList<>();

//    public DeadlineAdapter(List<Deadline> deadlines) {
//        this.deadlines = deadlines;
//    }

    @NonNull
    @Override
    public DeadlineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deadline_default, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeadlineAdapter.ViewHolder holder, int position) {
        Deadline currentDeadline = deadlines.get(position);
        holder.deadlineTitle.setText(currentDeadline.getDeadLineName());
        calculateAndDisplayRemainingTime(currentDeadline.getDeadLineDate(), holder.deadlineDate);
//        holder.itemView.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), currentDeadline.getDeadLineName(), Toast.LENGTH_SHORT).show();
//        });
    }
    private void calculateAndDisplayRemainingTime(Date deadlineDate, TextView deadlineDateTextView) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = deadlineDate.getTime() - currentTime;

        if (timeDifference > 0) {
            new CountDownTimer(timeDifference, 1000) {
                public void onTick(long millisUntilFinished) {
                    long seconds = millisUntilFinished / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    long days = hours / 24;

                    deadlineDateTextView.setText(days + "d " + hours % 24 + "h " + minutes % 60 + "m " + seconds % 60 + "s"+" "+"left");
                }

                public void onFinish() {
                    deadlineDateTextView.setText("Time's up!");
                }
            }.start();
        } else {
            deadlineDateTextView.setText("The target date has already passed.");
        }
    }
    @Override
    public int getItemCount() {
        return deadlines.size();
    }

    public void setDeadLines(List<Deadline> deadlines){
        this.deadlines = deadlines;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView deadlineTitle, deadlineDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deadlineTitle = itemView.findViewById(R.id.tvTitleDf);
            deadlineDate = itemView.findViewById(R.id.tvDeadlinedf);
        }
    }

}
