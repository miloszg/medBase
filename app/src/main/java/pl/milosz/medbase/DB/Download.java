package pl.milosz.medbase.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.mysql.jdbc.ResultSetMetaData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pl.milosz.medbase.Meds.Medication;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.LoginActivity.offlineMode;

public class Download extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static String twoj_stary = " ";
    public static Connection con;
    public static ArrayList<Medication> dbMeds=new ArrayList<>();
    public Download(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
    }


    protected String doInBackground(Void... params) {
        String url = "jdbc:mysql://192.168.0.129:3306/leki?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "admin";
        String pass = "admin";
        //"jdbc:mysql://192.168.0.129:3306/leki?useSSL=false&allowPublicKeyRetrieval=true";
        //"jdbc:mysql://192.168.0.52:3306/test?useSSL=false&allowPublicKeyRetrieval=true";
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
                    Statement st = con.createStatement();
                    result = "Database connection success\n";

                    ResultSet rs = st.executeQuery("SELECT * FROM leki.leki l\n" +
                            "INNER JOIN leki.pacjent_leki pl \n" +
                            "ON pl.leki_id = l.id\n" +
                            "INNER JOIN leki.pacjent p \n" +
                            "ON pl.pacjent_id = p.id\n" +
                            "WHERE p.id = 3;");

                    ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                    String test="tabletka";
                    while (rs.next()) {
                        result +=  rs.getString(2);
                        Medication medtest = new Medication(rs.getString(2), "2mg", test, R.drawable.round);
                        dbMeds.add(medtest);
                        Log.i("guwnp", rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) );
                        if(test.equals("tabletka")){
                            test="lek";
                        } else{
                            test="tabletka";
                        }
                    }
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


}
