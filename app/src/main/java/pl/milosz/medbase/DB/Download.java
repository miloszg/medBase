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

import static pl.milosz.medbase.LoginActivity.offlineMode;

public class Download extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static String twoj_stary = " ";
    public static Connection con;

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
                    twoj_stary = "Pawel rzadzi";
                    Statement st = con.createStatement();
                    result = "Database connection success\n";
                    //ResultSet rs = st.executeQuery("SELECT * FROM `test`.`leki` limit 10;");
                    ResultSet rs = st.executeQuery("Select \n" +
                            "l.nazwa,s.nazwa, k.nazwa,sp.nazwa,e.nazwa\n" +
                            "From leki.leki l\n" +
                            "INNER JOIN leki.leki_skladniki ls \n" +
                            "ON l.id = ls.leki_id\n" +
                            "INNER JOIN leki.skladniki s\n" +
                            "ON s.id = ls.skladniki_id\n" +
                            "INNER JOIN leki.leki_kategoria lk\n" +
                            "ON l.id = lk.leki_id\n" +
                            "INNER JOIN leki.kategoria k\n" +
                            "ON k.id = lk.kategoria_id\n" +
                            "INNER JOIN leki.leki_specjalnosc lsp\n" +
                            "ON l.id = lsp.leki_id\n" +
                            "INNER JOIN leki.specjalnosc sp\n" +
                            "ON sp.id = lsp.specjalnosc_id\n" +
                            "INNER JOIN leki.leki_efekt le\n" +
                            "ON l.id = le.leki_id\n" +
                            "INNER JOIN leki.efekt e\n" +
                            "ON e.id = le.efekt_id\n" +
                            " LIMIT 10;");
                    ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                    while (rs.next()) {
//                    result +=  rs.getString(2);
//                    Medication medtest = new Medication(rs.getString(2), "test", "test");
//                    medicationArrayList.add(medtest);
                        Log.i("guwnp", rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(3) + " " + rs.getString(4));
                    }
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
