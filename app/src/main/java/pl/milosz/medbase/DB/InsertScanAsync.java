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
import pl.milosz.medbase.R;

import static pl.milosz.medbase.LoginActivity.offlineMode;

public class InsertScanAsync extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static String twoj_stary = " ";
    public static Connection con;

    public InsertScanAsync(Context context) {
        this.context = context;

    }
    protected void onPreExecute() {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
    }

    protected String doInBackground(Void... params) {
        String url = "jdbc:mysql://192.168.0.129:3306/leki?useSSL=false&allowPublicKeyRetrieval=true";
        //String url = "jdbc:mysql://192.168.0.52:3306/test?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "admin";
        String pass = "admin";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            System.out.println("JAVA: Class.forName() error");
            e.printStackTrace();
        }
        try {
            if(offlineMode==false) {
                Connection con = DriverManager.getConnection(url, user, pass);
                if (con != null) {
                    twoj_stary = "Dodawanie udane";
                    Statement st = con.createStatement();
                    result = "Database connection success\n";
                    st.executeUpdate("INSERT INTO `leki`.`leki` (nazwa,informacje,dawkowanie) VALUES ('Scorbolamid','tabletki drazowane','2 tabletki 3 razy na dobe');");
                    Medication medtest = new Medication("SCAN MED", "test SCAN", "test SCAN", R.drawable.ic_meds);
                    Download.dbMeds.add(medtest);
                }
                Log.i("guwno", result);
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


}
