package com.bit.timeliner;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
    private List<Timeline> timelines = new ArrayList<>();

//    public TimelineAdapter(List<Timeline> timelines) {
//        this.timelines = timelines;
//    }

    @NonNull
    @Override
    public TimelineAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimelineAdapter.ViewHolder holder, int position) {
        Timeline currentTimeline = timelines.get(position);
        holder.timelineTitle.setText(currentTimeline.getTimeLineName());
    }
    @Override
    public int getItemCount() {
        return timelines.size();
    }

    public void setTimelines(List<Timeline> timelines){
        this.timelines = timelines;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timelineTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timelineTitle = itemView.findViewById(R.id.tvTimelineTitle);;
        }
    }

}
