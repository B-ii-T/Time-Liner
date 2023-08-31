package com.bit.timeliner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "timeline_table")
public class Timeline {
    @PrimaryKey(autoGenerate = true)
    private int timeLineId;
    private String timeLineName;

    public Timeline(String timeLineName) {
        this.timeLineName = timeLineName;
    }

    public int getTimeLineId() {
        return timeLineId;
    }

    public String getTimeLineName() {
        return timeLineName;
    }

    public void setTimeLineId(int timeLineId) {
        this.timeLineId = timeLineId;
    }

    public void setTimeLineName(String timeLineName) {
        this.timeLineName = timeLineName;
    }
}
