package pl.milosz.medbase.CalendarView;

/**
 * Obiekt okeślający kontretne wydarzenie z kalendarza
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class Events {
    String date;
    String time;
    String description;
    Long timeInMillis;

    Events(String date, String time, String description, Long timeInMillis) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.timeInMillis = timeInMillis;
    }

    Long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(Long timeInMillis) {
        this.timeInMillis = timeInMillis;
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

    String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
