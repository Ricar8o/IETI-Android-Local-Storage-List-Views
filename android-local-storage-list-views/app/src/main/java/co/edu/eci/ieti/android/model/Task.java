package co.edu.eci.ieti.android.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {
    @PrimaryKey
    @NonNull
    private long id;
    private String description;
    private int priority;
    private String assignedTo;
    private Date dueDate;

    // public Task(){}

    public Task(long id, String description, int priority, String assignedTo, Date dueDate) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString()
    {
        return description + '\n' + priority + '\n' + assignedTo + '\n' + dueDate;
        //return "Task{id="+ id + ", description='" + description + '\'' + ", priority=" + priority + ", assignedTo=" + assignedTo + ", dueDate=" + dueDate + '}';
    }
}
