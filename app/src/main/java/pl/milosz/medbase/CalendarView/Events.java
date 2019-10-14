package pl.milosz.medbase.CalendarView;

public class Events {
    String date;
    String time;
    String desription;
    int index;
    public Events(int index, String date, String time, String desription) {
        this.index = index;
        this.date = date;
        this.time = time;
        this.desription = desription;
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

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }
}
