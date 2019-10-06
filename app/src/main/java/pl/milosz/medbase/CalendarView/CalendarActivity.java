package pl.milosz.medbase.CalendarView;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pl.milosz.medbase.R;

public class CalendarActivity extends AppCompatActivity {
    ArrayList<Date> dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dates=new ArrayList<>();
        Date today=new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);

        final CalendarPickerView datePicker = findViewById(R.id.calendar);
        datePicker.init(today,nextYear.getTime())
               // .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDate(today);


        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                dates.add(date);


                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);

               // String selectedDate ="" + calSelected.get(Calendar.DAY_OF_MONTH)+" "+(calSelected.get(Calendar.MONTH)+1) +""+calSelected.get(Calendar.YEAR);
                Toast.makeText(CalendarActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDateUnselected(Date date) {
                datePicker.highlightDates(dates);
            }
        });
    }

    List<Date> datesList = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();
}
