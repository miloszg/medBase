package pl.milosz.medbase.Alerts;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
/**
 * Klasa, w której tworzone są kanały komunikacyjne
 * Kanał 1 przypisany jest do powiadomień z alarmem
 * Kanał 2 przypisany jest do powiadomień o nadchodzącycm wydarzeniu
 * Kanał 3 przypisany jest do powiadomień odnośnie poboru leku
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class CreateChannel extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";
    public static final String CHANNEL_3_ID = "channel3";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }


    private void createNotificationChannels() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );
            channel1.setDescription("This is Channel 2");

            NotificationChannel channel3 = new NotificationChannel(
                    CHANNEL_3_ID,
                    "Channel 3",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel3.enableLights(true);
            channel3.enableVibration(true);
            channel1.setDescription("This is Channel 3");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);

        }
    }


}
