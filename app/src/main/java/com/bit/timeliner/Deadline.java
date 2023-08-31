package com.bit.timeliner;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "deadline_table",
        foreignKeys = @ForeignKey(entity = Timeline.class,
                parentColumns = "timeLineId",
                childColumns = "timelineId",
                onDelete = ForeignKey.CASCADE))
public class Deadline {
    @PrimaryKey(autoGenerate = true)
    private int deadLineId;
    private int timelineId;
    private String deadLineName;
    private Date deadLineDate;

    public Deadline(int timelineId, String deadLineName, Date deadLineDate) {
        this.timelineId = timelineId;
        this.deadLineName = deadLineName;
        this.deadLineDate = deadLineDate;
    }

    public int getDeadLineId() {
        return deadLineId;
    }

    public int getTimelineId() {
        return timelineId;
    }

    public String getDeadLineName() {
        return deadLineName;
    }

    public Date getDeadLineDate() {
        return deadLineDate;
    }

    public void setDeadLineId(int deadLineId) {
        this.deadLineId = deadLineId;
    }

    public void setTimelineId(int timelineId) {
        this.timelineId = timelineId;
    }

    public void setDeadLineName(String deadLineName) {
        this.deadLineName = deadLineName;
    }

    public void setDeadLineDate(Date deadLineDate) {
        this.deadLineDate = deadLineDate;
    }
}