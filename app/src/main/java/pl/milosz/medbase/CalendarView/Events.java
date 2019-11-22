package pl.milosz.medbase.CalendarView;

public class Events {
    String date;
    String time;
    String description;
    Long timeInMillis;
    int index;
    public Events(int index, String date, String time, String description, Long timeInMillis) {
        this.index = index;
        this.date = date;
        this.time = time;
        this.description = description;
        this.timeInMillis = timeInMillis;
    }

    public Long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(Long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
