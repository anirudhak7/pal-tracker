package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {

    private long id = -1L;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }



    public TimeEntry() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()){
            return false;
        }
        TimeEntry other = (TimeEntry)obj;
        return other.getId() == this.getId() && other.projectId == this.projectId
                && other.userId == this.userId
                && other.hours == this.hours && datesEqual(this.date, other.date);
    }

    private static boolean datesEqual(LocalDate thisDate, LocalDate otherDate){
        if(thisDate == null && otherDate == null)
            return true;
        return thisDate.equals(otherDate);
    }
}
