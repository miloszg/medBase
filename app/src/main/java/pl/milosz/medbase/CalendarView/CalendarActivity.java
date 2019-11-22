package pl.milosz.medbase.CalendarView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import pl.milosz.medbase.Alerts.NotificationTextFragment;
import pl.milosz.medbase.Alerts.TimePickerFragment;
import pl.milosz.medbase.R;

public class CalendarActivity extends AppCompatActivity implements android.app.TimePickerDialog.OnTimeSetListener, NotificationTextFragment.NotificationTextListener {
    public static ArrayList<Date> dates = new ArrayList<>();
    View view;
    Calendar c;
    String selectedDate;
    Date eventTargetDate;
    int index = 0;
    CalendarPickerView datePicker;
    public static ArrayList<Events> events = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Date today = new Date();

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        c = Calendar.getInstance();
        datePicker = findViewById(R.id.calendar);

        datePicker.init(today, nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE)
                .withSelectedDate(today);
        datePicker.clearHighlightedDates();
        datePicker.highlightDates(dates);
        view = new View(this);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date targetDate) {
                eventTargetDate = targetDate;
                selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(targetDate);
                c.setTime(targetDate);
                if (dates.contains(targetDate)) {
                    datePicker.clearHighlightedDates();
                    datePicker.highlightDates(dates);
                    Intent calIntent = new Intent(getApplicationContext(), EventActivity.class);
                    for (Events e : events) {
                        if (e.date.equals(selectedDate)) {
                            calIntent.putExtra("index", e.getIndex());
                            startActivity(calIntent);
                        }
                    }

                } else {
                    Calendar calSelected = Calendar.getInstance();
                    calSelected.setTime(targetDate);
                    sendOnTime(view);
                }
            }

            @Override
            public void onDateUnselected(Date date) {
            }
        });
    }

    public void sendOnTime(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void sendOnText() {
        NotificationTextFragment textFragment = new NotificationTextFragment();
        textFragment.show(getSupportFragmentManager(), "notification text");
    }


    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        sendOnText();
    }


    @Override
    public void getText(String eventDescription) {

        dates.add(eventTargetDate);
        datePicker.clearHighlightedDates();
        datePicker.highlightDates(dates);
        String eventTime = +c.get(Calendar.HOUR_OF_DAY) + ":" + String.format("%02d",c.get(Calendar.MINUTE));
        Events event = new Events(index, selectedDate, eventTime, eventDescription, c.getTimeInMillis());
        Log.i("guwno",String.valueOf(event));
        events.add(event);
        Intent calIntent = new Intent(this, EventActivity.class);
        calIntent.putExtra("index", index);
        index++;

        startActivity(calIntent);
    }

}
