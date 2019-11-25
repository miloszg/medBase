package pl.milosz.medbase.CalendarView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import pl.milosz.medbase.Alerts.NotificationReceiver;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.CalendarView.CalendarActivity.events;

/**
 * Aktywność, która ma na celu wyswietlenia informacji o indywidualnym wydarzeniu.
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class EventActivity extends AppCompatActivity {
    AlarmManager alarmManagerCalendar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_event_dialog);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        final int index = getIntent().getExtras().getInt("index", -1);
        timeTextView.setText(events.get(index).time);
        descriptionTextView.setText(events.get(index).description);

        alarmManagerCalendar = (AlarmManager) getSystemService(ALARM_SERVICE);
        SwitchCompat switchCompat = findViewById(R.id.eventSwitch);

        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                intent = new Intent(this, NotificationReceiver.class);
                intent.putExtra("text", events.get(index).getDescription());
                intent.putExtra("channel", "two");
                intent.putExtra("timeInMillis", events.get(index).getTimeInMillis());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, 0);
                Toast.makeText(EventActivity.this, "Alarm włączono!\n Zostaniesz powiadomiony godzinę przed wydarzeniem!", Toast.LENGTH_LONG).show();
                alarmManagerCalendar.setExact(AlarmManager.RTC_WAKEUP, events.get(index).getTimeInMillis() - 3600 * 1000, pendingIntent);
            } else {
                Toast.makeText(EventActivity.this, "Alarm wyłączono", Toast.LENGTH_SHORT).show();
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 2, intent, 0);
                alarmManagerCalendar.cancel(pendingIntent);
            }
        });

    }
}
