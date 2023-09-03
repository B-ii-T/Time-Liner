package com.bit.timeliner;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {
    private List<Timeline> timelines = new ArrayList<>();
    private OnTimelineClickListener listener;
    public void setOnTimelineClickListener(OnTimelineClickListener listener) {
        this.listener = listener;
    }

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
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                if (position != RecyclerView.NO_POSITION) {
                    listener.onTimelineClick(currentTimeline.getTimeLineId(), currentTimeline.getTimeLineName());
                }
            }
        });
//        holder.itemView.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), currentTimeline.getTimeLineName(), Toast.LENGTH_SHORT).show();
//        });
    }
    @Override
    public int getItemCount() {
        return timelines.size();
    }
    public interface OnTimelineClickListener {
        void onTimelineClick(int id, String name);
    }

    public void setTimelines(List<Timeline> timelines){
        this.timelines = timelines;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView timelineTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            timelineTitle = itemView.findViewById(R.id.tvTimelineTitle);
        }
    }

}
