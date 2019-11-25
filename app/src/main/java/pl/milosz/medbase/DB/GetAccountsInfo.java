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

import static pl.milosz.medbase.LoginActivity.offlineMode;
/**
 * Asynchroniczna akcja odpowiadająca za sciągnięcie informacji o użytkownikach
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class GetAccountsInfo extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static Connection con;
    public static ArrayList<User> users = new ArrayList<>();
    private static final String TAG = "GetAccountsInfo";

    public GetAccountsInfo(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {
        Log.i(TAG," pre execute");
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
                    ResultSet rs = st.executeQuery("SELECT * FROM `leki`.`pacjent` limit 10;");
                    while (rs.next()) {
                        User userTest = new User(rs.getInt(1), rs.getString(2), rs.getString(6));
                        users.add(userTest);
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
        Log.i(TAG," post execute");
    }


}
