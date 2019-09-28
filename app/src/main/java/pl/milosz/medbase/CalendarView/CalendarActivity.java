package pl.milosz.medbase.CalendarView;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pl.milosz.medbase.R;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    List<Date> datesList = new ArrayList<>();
    List<Events> eventsList = new ArrayList<>();
}
