package pl.milosz.medbase.Alerts;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import pl.milosz.medbase.MainActivity;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.CreateChannels.CHANNEL_1_ID;
import static pl.milosz.medbase.CreateChannels.CHANNEL_2_ID;

public class AlertsActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);

        notificationManager=NotificationManagerCompat.from(this);
        editTextTitle=findViewById(R.id.edit_text_title);
        editTextMessage=findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View view){
        String title=editTextTitle.getText().toString();
        String message=editTextMessage.getText().toString();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0,activityIntent, 0);
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage",message);

        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification= new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_add)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.GREEN)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher,"Toast",actionIntent)
                .build();

        notificationManager.notify(1,notification);
    }

    public void sendOnChannel2(View view){
        String title=editTextTitle.getText().toString();
        String message=editTextMessage.getText().toString();
        Notification notification= new NotificationCompat.Builder(this,CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_meds)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2,notification);
        Toast.makeText(this, "cos", Toast.LENGTH_SHORT).show();
    }
}
