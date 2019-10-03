package pl.milosz.medbase.Alerts;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;

import pl.milosz.medbase.MainActivity;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.Alerts.CreateChannels.CHANNEL_1_ID;
import static pl.milosz.medbase.Alerts.CreateChannels.CHANNEL_2_ID;

public class AlertsActivity extends AppCompatActivity implements android.app.TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private NotificationManagerCompat notificationManager;
    LinearLayout linearLayout;
    SwitchCompat sw, sw1, sw2, sw3, sw4, sw5;
    int index = 0;
    private TextView timePickerText;
    private TextView datePickerText;
    private Calendar c;
    AlarmManager alarmManager, alarmManager1, alarmManager2, alarmManager3, alarmManager4, alarmManager5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        linearLayout = findViewById(R.id.alertLayout);
        notificationManager = NotificationManagerCompat.from(this);
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


    public void sendOnTime(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void sendOnDate(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    public void sendOnChannel1(View view) {
        if (!notificationManager.areNotificationsEnabled()) {
            openNotificationsSettings();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isChannelBlocked(CHANNEL_1_ID)) {
            openChannelSettings(CHANNEL_1_ID);
            return;
        }

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);

        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.chadson);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_add)
                .setLargeIcon(picture)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(picture)
                        .bigLargeIcon(null))
                .setContentTitle("Super powiadomienie")
                .setContentText("Mordo")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                //.addAction(R.mipmap.ic_launcher, "PIJ PIWO", actionIntent)
                .build();

        notificationManager.notify(1, notification);

    }

    private void openNotificationsSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            startActivity(intent);
        } else {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }

    @RequiresApi(26)
    private boolean isChannelBlocked(String channelId) {
        NotificationManager manager = getSystemService(NotificationManager.class);
        NotificationChannel channel = manager.getNotificationChannel(channelId);

        return channel != null && channel.getImportance() == NotificationManager.IMPORTANCE_NONE;
    }

    @RequiresApi(26)
    private void openChannelSettings(String channelId) {
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        startActivity(intent);
    }

    public void sendOnChannel2(View view) {

        Bitmap artWork = BitmapFactory.decodeResource(getResources(), R.drawable.chadson);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_add)
                .setContentTitle("Ble ble ble")
                .setContentText("cos tam")
                .setLargeIcon(artWork)
                .setGroup("example")
                .addAction(R.drawable.ic_add, "ad", null)
                .addAction(R.drawable.ic_heart, "he", null)
                .addAction(R.drawable.ic_profile, "prof", null)
                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                )
                .setSubText("Sub text")
//               .setStyle(new NotificationCompat.InboxStyle()
//                        .addLine("Lole1")
//                        .addLine("Lole2")

                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(2, notification);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);


        switch (index) {
            case 0:
                dodajSwitch(sw, alarmManager);
                break;
            case 1:
                dodajSwitch(sw1, alarmManager1);
                break;
            case 2:
                dodajSwitch(sw2, alarmManager2);
                break;
//            case 3:
//                dodajSwitch(sw3);
//                break;
//            case 4:
//                dodajSwitch(sw4);
//                break;
            default:
                break;
        }


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
        insideManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationReceiver.class);
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, index, intent, 0);
        insideManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime()) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + " WEŹ TABLETKE PROSZEEEEEEEE";
        sw = new SwitchCompat(this);
        sw.setId(index);
        sw.setChecked(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 30, 30);
        sw.setLayoutParams(layoutParams);
        sw.setTextSize(20);
        sw.setText(currentDate);
        final SwitchCompat finalSw = sw;
        final AlarmManager finalInsideManager = insideManager;
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    finalInsideManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                    Toast.makeText(AlertsActivity.this, "Włączony", Toast.LENGTH_SHORT).show();
                } else {
                    finalInsideManager.cancel(pendingIntent);
                    Toast.makeText(AlertsActivity.this, "Wyłączony", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (linearLayout != null) {
            linearLayout.addView(sw);
        }
        index++;
    }
}
