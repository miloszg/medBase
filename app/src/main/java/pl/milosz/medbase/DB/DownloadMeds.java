package pl.milosz.medbase.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pl.milosz.medbase.Meds.Medication;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.LoginActivity.offlineMode;
import static pl.milosz.medbase.Meds.MedsActivity.adapter;

/**
 * Asynchroniczna akcja odpowiadająca za ściągnięcie danych o przyjmowanych lekach z bazy danych
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class DownloadMeds extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static Connection con;
    public static ArrayList<Medication> dbMeds = new ArrayList<>();

    public DownloadMeds(Context context) {
        this.context = context;
    }

    private static final String TAG = "DownloadMeds";

    protected void onPreExecute() {
        Log.i(TAG, " pre execute");
    }


    protected String doInBackground(Void... params) {
        String url = "jdbc:mysql://192.168.0.129:3306/leki?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "admin";
        String pass = "admin";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("JAVA: Class.forName() error");
            e.printStackTrace();
        }
        try {
            if (offlineMode == false) {
                Connection con = DriverManager.getConnection(url, user, pass);
                if (con != null) {
                    Statement st = con.createStatement();
                    result = "Database connection success\n";

                    ResultSet rs = st.executeQuery("SELECT * FROM leki.leki l\n" +
                            "INNER JOIN leki.pacjent_leki pl \n" +
                            "ON pl.leki_id = l.id\n" +
                            "INNER JOIN leki.pacjent p \n" +
                            "ON pl.pacjent_id = p.id\n" +
                            "WHERE p.id = 3;");
                    String test = "tabletka";
                    dbMeds.clear();
                    while (rs.next()) {
                        result += rs.getString(2);

                        Medication medtest = new Medication(rs.getString(2), "1 raz na dobe", test, R.drawable.round);
                        dbMeds.add(medtest);

                        Log.i(TAG, rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
                        if (test.equals("tabletka")) {
                            test = "lek";
                        } else {
                            test = "tabletka";
                        }

                    }
                    adapter.notifyDataSetChanged();

                }
            } else {
                Medication medication1=new Medication("Stoperan1","2 razy na dobe","Sanofi",R.drawable.round);
                Medication medication2=new Medication("Stoperan2","2 razy na dobe","Sanofi",R.drawable.round);
                Medication medication3=new Medication("Stoperan3","2 razy na dobe","Sanofi",R.drawable.round);
                DownloadMeds.dbMeds.add(medication1);
                DownloadMeds.dbMeds.add(medication2);
                DownloadMeds.dbMeds.add(medication3);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return "Complete";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i(TAG, " post execute");
        adapter.notifyDataSetChanged();
    }


}
