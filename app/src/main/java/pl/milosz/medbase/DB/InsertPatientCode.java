package pl.milosz.medbase.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static pl.milosz.medbase.CodeActivity.fourDigit;
import static pl.milosz.medbase.LoginActivity.offlineMode;
import static pl.milosz.medbase.LoginActivity.patient_id;

/**
 * Asynchroniczna akcja odpowiadająca za dodanie kodu dostępu do bazy danych
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class InsertPatientCode extends AsyncTask<Void, Void, String> {
    private Context context;
    private String result;
    public static Connection con;
    private static final String TAG = "InsertPatientCode";

    public InsertPatientCode(Context context) {
        this.context = context;

    }

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
            if (!offlineMode) {
                Connection con = DriverManager.getConnection(url, user, pass);
                if (con != null) {
                    Statement st = con.createStatement();
                    result = "Database connection success\n";
                    String code = String.valueOf(fourDigit);
                    st.executeUpdate("INSERT INTO `leki`.`pacjent_kod` (pacjent_id,pacjent_kod) VALUES ('" + patient_id + "'," + code + ");");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Complete";
    }

    @Override
    protected void onPostExecute(String s) {
        Log.i(TAG, " post execute");
    }


}
