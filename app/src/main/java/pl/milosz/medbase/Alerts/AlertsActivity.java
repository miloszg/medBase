package pl.milosz.medbase.Alerts;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import pl.milosz.medbase.R;

public class AlertsActivity extends AppCompatActivity implements android.app.TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, NotificationTextFragment.NotificationTextListener {
    LinearLayout linearLayout;
    SwitchCompat sw, sw1, sw2, sw3, sw4, sw5;
    int index = switchArray.size()+1;
    private Calendar c;
    AlarmManager alarmManager0, alarmManager1, alarmManager2, alarmManager3, alarmManager4, alarmManager5;
    public static ArrayList<SwitchCompat> switchArray = new ArrayList<>();
    public static String notificationStringText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        linearLayout = findViewById(R.id.alertLayout);
        renderList();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnDate(view);
            }
        });
    }

    @Override
    protected void onPause() {
        linearLayout.removeAllViews();
        super.onPause();
    }

    public void sendOnText(){
        NotificationTextFragment textFragment= new NotificationTextFragment();
        textFragment.show(getSupportFragmentManager(), "notification text");
    }

    public void sendOnTime(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void sendOnDate(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        sendOnText();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        sendOnTime(view);
    }

    public void dodajSwitch(SwitchCompat sw, AlarmManager insideManager) {
        String notificationFullText = notificationStringText+"\n" + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ", " + DateFormat.getDateInstance().format(c.getTime());
        Log.i("guwno index",String.valueOf(index));
        insideManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.putExtra("text",notificationFullText);
        intent.putExtra("channel","one");
        final PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this, index, intent, 0);
        insideManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent1);

        sw = new SwitchCompat(this);
        sw.setId(index);
        sw.setChecked(true);
        sw.setBackground(getDrawable(R.drawable.customshape));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 10, 30);
        sw.setLayoutParams(layoutParams);
        sw.setTextColor(Color.GRAY);
        sw.setPadding(10,10,10,10);
        sw.setTextSize(19);
        sw.setGravity(Gravity.LEFT);
        sw.setText(notificationFullText);
        final SwitchCompat finalSw = sw;
        switchArray.add(finalSw);
        final AlarmManager finalInsideManager = insideManager;
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    finalInsideManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent1);
                    Toast.makeText(AlertsActivity.this, "Włączony", Toast.LENGTH_SHORT).show();
                } else {
                    finalInsideManager.cancel(pendingIntent1);
                    Toast.makeText(AlertsActivity.this, "Wyłączony", Toast.LENGTH_SHORT).show();
                }
            }
        });
        renderList();
        index++;
    }

    public void renderList(){
        linearLayout.removeAllViews();
        if (linearLayout != null) {
            for(SwitchCompat switchCompat:switchArray) {
                linearLayout.addView(switchCompat);
            }
        }
    }

    @Override
    public void getText(String notificationText) {
        notificationStringText=notificationText;
        Log.i("guwno", notificationText);
        switch (index) {
            case 0:
                //dodajSwitch(sw, alarmManager0);
                break;
            case 1:
                dodajSwitch(sw1, alarmManager1);
                break;
            case 2:
                dodajSwitch(sw2, alarmManager2);
                break;
            case 3:
                dodajSwitch(sw3, alarmManager3);
                break;
            case 4:
                dodajSwitch(sw4, alarmManager4);
                break;
            case 5:
                dodajSwitch(sw5, alarmManager5);
                break;
            default:
                break;
        }
    }
}
