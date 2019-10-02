package pl.milosz.medbase.Alerts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

import pl.milosz.medbase.R;

public class TimePickerDialog extends AppCompatActivity implements android.app.TimePickerDialog.OnTimeSetListener {

    private TextView alarmTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_dialog);

        alarmTextView=findViewById(R.id.alarmText);
        Button button=findViewById(R.id.timeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker=new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });


    }




    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView textView=findViewById(R.id.timeText);
        textView.setText("Hour: "+hourOfDay + " Minute: "+minute);

        Calendar c= Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        updateTimeText(c);
       // startAlarm(c);
    }

    private void updateTimeText(Calendar c) {
        String timeText="Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        alarmTextView.setText(timeText);
    }

//    private void startAlarm(Calendar c) {
////        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
////        Intent intent=new Intent(this, AlertReceiver.class);
////        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
////        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),pendingIntent);
//    }
//
//    private void cancelAlarm() {
////        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
////        Intent intent=new Intent(this, AlertReceiver.class);
////        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
////        alarmManager.cancel(pendingIntent);
////        alarmTextView.setText("Cancelled");
 //   }
}
