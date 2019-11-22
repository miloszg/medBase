package pl.milosz.medbase.DB;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.milosz.medbase.R;

import static pl.milosz.medbase.Alerts.CreateChannel.CHANNEL_1_ID;
import static pl.milosz.medbase.LoginActivity.offlineMode;

public class DownloadTest extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    NotificationManager notificationManager;
    public static String twoj_stary = " ";
    public static Connection con;
    String url = "jdbc:mysql://192.168.0.129:3306/leki?useSSL=false&allowPublicKeyRetrieval=true";
    String user = "admin";
    String pass = "admin";

    //"jdbc:mysql://192.168.0.129:3306/leki?useSSL=false&allowPublicKeyRetrieval=true";
    //"jdbc:mysql://192.168.0.52:3306/test?useSSL=false&allowPublicKeyRetrieval=true";
    public DownloadTest(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
    }


    protected String doInBackground(Void... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("JAVA: Class.forName() error");
            e.printStackTrace();
        }
        try {
            if (!offlineMode) {
                Connection con = DriverManager.getConnection(url, user, pass);
                if (con != null) {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("Select * from leki  LIMIT 10;");
                    while (rs.next())
                        System.out.println(rs.getInt(1) + "  " + rs.getString(2));
                    con.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Complete";
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, twoj_stary, Toast.LENGTH_SHORT).show();
    }

    private void sendNotification1(Context context) {
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_meds)
                .setContentTitle("Twoje Powiadomienie - Przpomnienie")
                .setColor(Color.GREEN)
                .setSound(defaultSound)
                .setGroup("example")
                .setSubText("MedBase")
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Przypomnienie polega na: ")
                        .addLine("dodatkowa linia na rzeczy"))
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);
    }

    private void openNotificationsSettings() {
    }
}
