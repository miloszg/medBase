package pl.milosz.medbase.Alerts;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import pl.milosz.medbase.MainActivity;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.Alerts.CreateChannel.CHANNEL_1_ID;
import static pl.milosz.medbase.Alerts.CreateChannel.CHANNEL_2_ID;

public class NotificationReceiver extends BroadcastReceiver {
    Context contextGlobal;
    String notText;
    String channel;

    @Override
    public void onReceive(Context context, Intent intent) {
        contextGlobal = context;
        notText = intent.getExtras().getString("text", " powiadomienie");
        channel = intent.getExtras().getString("channel", "null");
        if (channel.equals("one")) {
            channel = "";
            sendNotification1(context);
        } else if (channel.equals("two")) {
            channel = "";
            sendNotification2(context);
        }
    }

    private void sendNotification1(Context context) {
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (!notificationManager.areNotificationsEnabled()) {
            openNotificationsSettings();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isChannelBlocked(CHANNEL_1_ID)) {
            openChannelSettings(CHANNEL_1_ID);
            return;
        }
        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_meds)
                .setContentTitle("Twoje Powiadomienie - Przpomnienie")
                .setContentText(notText)
                .setColor(Color.GREEN)
                .setSound(defaultSound)
                .setGroup("example")
                .setSubText("MedBase")
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Przypomnienie polega na: ")
                        .addLine("dodatkowa linia na rzeczy"))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }

    private void sendNotification2(Context context) {
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (!notificationManager.areNotificationsEnabled()) {
            openNotificationsSettings();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && isChannelBlocked(CHANNEL_2_ID)) {
            openChannelSettings(CHANNEL_2_ID);
            return;
        }
        Intent activityIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_calendar)
                .setContentTitle("Twoje Powiadomienie - Kalendarz")
                .setContentText(notText)
                .setColor(Color.GREEN)
                .setSound(defaultSound)
                .setGroup("example")
                .setSubText("MedBase")
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Wydarzenie")
                        .addLine("dodatkowa linia na rzeczy"))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2, notification);
    }

    private void openNotificationsSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, contextGlobal.getPackageName());
            contextGlobal.startActivity(intent);
        } else {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + contextGlobal.getPackageName()));
            contextGlobal.startActivity(intent);
        }
    }

    @RequiresApi(26)
    private boolean isChannelBlocked(String channelId) {
        NotificationManager manager = contextGlobal.getSystemService(NotificationManager.class);
        NotificationChannel channel = manager.getNotificationChannel(channelId);

        return channel != null && channel.getImportance() == NotificationManager.IMPORTANCE_NONE;
    }

    @RequiresApi(26)
    private void openChannelSettings(String channelId) {
        Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, contextGlobal.getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        contextGlobal.startActivity(intent);
    }
}





    /*
    --------------------------------------------------------------
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

    ---------------------------------------------------------------------------

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
 */