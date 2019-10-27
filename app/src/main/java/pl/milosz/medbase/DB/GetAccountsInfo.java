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

public class GetAccountsInfo extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static String twoj_stary = " ";
    public static Connection con;

    public GetAccountsInfo(Context context) {
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
                twoj_stary = "dane uzytkownika pobrane";
                Statement st = con.createStatement();
                result = "Database connection success\n";
                ResultSet rs = st.executeQuery("SELECT * FROM `test`.`pacjent` limit 10;");
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                while (rs.next()) {
                    result += rs.getString(2) + " - " + rs.getString(4);
                }
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
