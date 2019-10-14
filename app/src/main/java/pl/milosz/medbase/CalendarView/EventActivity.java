package pl.milosz.medbase.CalendarView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.milosz.medbase.R;

import static pl.milosz.medbase.CalendarView.CalendarActivity.events;

public class EventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_event_dialog);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        int index = getIntent().getExtras().getInt("index",-1);
        timeTextView.setText(events.get(index).time);
        descriptionTextView.setText(events.get(index).desription);
        Log.i("guwnoo", events.get(index).date);
    }
}
