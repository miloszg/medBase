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
    ArrayList<Date> dates;
    View view;
    Calendar c;
    String selectedDate;
    int index=0;
    public static ArrayList<Events> events=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dates=new ArrayList<>();
        Date today=new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        c = Calendar.getInstance();
        final CalendarPickerView datePicker = findViewById(R.id.calendar);
        datePicker.init(today,nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.SINGLE)
                .withSelectedDate(today);

        view=new View(this);

        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date targetDate) {

                selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(targetDate);

                if (dates.contains(targetDate)) {
//                    Toast.makeText(CalendarActivity.this, "ta data juz jest - usuwamy", Toast.LENGTH_SHORT).show();
//                    dates.remove(dates.indexOf(targetDate));
                    datePicker.clearHighlightedDates();
                    datePicker.highlightDates(dates);
                    Intent calIntent=new Intent(getApplicationContext(),EventActivity.class);
                    Log.i("gu selected",selectedDate);
                    for(Events e : events){
                        Log.i("gu w petli",e.date);
                        if(e.date.equals(selectedDate)){
                            calIntent.putExtra("index",e.getIndex());
                            startActivity(calIntent);
                        }
                    }

                } else {
                    dates.add(targetDate);
                    datePicker.clearHighlightedDates();
                    datePicker.highlightDates(dates);
                    Calendar calSelected = Calendar.getInstance();
                    calSelected.setTime(targetDate);
                    Log.i("guwno array:", dates.toString());
                    // String selectedDate ="" + calSelected.get(Calendar.DAY_OF_MONTH)+" "+(calSelected.get(Calendar.MONTH)+1) +""+calSelected.get(Calendar.YEAR);
                    // Toast.makeText(CalendarActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
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

    public void sendOnText(){
        NotificationTextFragment textFragment= new NotificationTextFragment();
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

        String eventTime =  + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        Events event=new Events(index,selectedDate,eventTime,eventDescription);
        events.add(event);
        Intent calIntent=new Intent(this,EventActivity.class);
        calIntent.putExtra("index",index);
        index++;
        startActivity(calIntent);
    }





}
