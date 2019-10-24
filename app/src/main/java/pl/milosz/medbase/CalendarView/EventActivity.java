package pl.milosz.medbase.CalendarView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import pl.milosz.medbase.Alerts.NotificationReceiver;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.CalendarView.CalendarActivity.events;

public class EventActivity extends AppCompatActivity {
    AlarmManager alarmManagerCalendar;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_event_dialog);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        final int index = getIntent().getExtras().getInt("index",-1);
        timeTextView.setText(events.get(index).time);
        descriptionTextView.setText(events.get(index).desription);

        alarmManagerCalendar=(AlarmManager) getSystemService(ALARM_SERVICE);
        SwitchCompat switchCompat=findViewById(R.id.eventSwitch);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
                    intent.putExtra("text",events.get(index).getDesription());
                    intent.putExtra("channel","two");
                    pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), index, intent, 0);
                    Toast.makeText(EventActivity.this, "Alarm włączono", Toast.LENGTH_SHORT).show();
                    alarmManagerCalendar.setExact(AlarmManager.RTC_WAKEUP, events.get(index).getTimeInMillis(), pendingIntent);
                } else {
                    Toast.makeText(EventActivity.this, "Alarm wyłączono", Toast.LENGTH_SHORT).show();
                    alarmManagerCalendar.cancel(pendingIntent);
                }
            }
        });

    }
}
