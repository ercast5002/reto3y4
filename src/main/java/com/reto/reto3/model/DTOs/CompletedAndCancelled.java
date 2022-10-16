package com.reto.reto3.model.DTOs;

public class CompletedAndCancelled {
    private long completed;
    private long cancelled;
    
    public CompletedAndCancelled(long completed, long cancelled) {
        this.completed = completed;
        this.cancelled = cancelled;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public long getCancelled() {
        return cancelled;
    }

    public void setCancelled(long cancelled) {
        this.cancelled = cancelled;
    }

    
}
