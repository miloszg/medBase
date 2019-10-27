package pl.milosz.medbase.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pl.milosz.medbase.Meds.Medication;

import static pl.milosz.medbase.Meds.MedsActivity.medicationArrayList;

public class InsertCustomMedAsync extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static String twoj_stary = " ";
    public static Connection con;

    public InsertCustomMedAsync(Context context) {
        this.context = context;

    }
    protected void onPreExecute() {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
    }

    protected String doInBackground(Void... params) {
        String url = "jdbc:mysql://192.168.0.52:3306/test?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "admin";
        String pass = "admin";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("JAVA: Class.forName() error");
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                twoj_stary = "Dodawanie udane";
                Statement st = con.createStatement();
                result = "Database connection success\n";
                st.executeUpdate("INSERT INTO `test`.`leki` VALUES (1006,'CUSTOM MED','test CUSTOM','test CUSTOM');");
                Medication medtest = new Medication("CUSTOM MED", "test CUSTOM", "test CUSTOM");
                medicationArrayList.add(medtest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.i("guwno", result);
        return "Complete";
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, twoj_stary, Toast.LENGTH_SHORT).show();
    }


}