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

import pl.milosz.medbase.ScanActivity;

import static pl.milosz.medbase.LoginActivity.offlineMode;
import static pl.milosz.medbase.LoginActivity.patient_id;

/**
 * Asynchroniczna akcja odpowiadająca za dodanie zeskanowanego leku z kodu QR do bazy danych jako
 * lek przyjmowany przez użytkownika
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class InsertScanAsync extends AsyncTask<Void, Void, String> {
    Context context;
    String result;

    public static Connection con;
    private static final String TAG = "InsertScanAsync";

    public InsertScanAsync(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {
        Log.i(TAG, "pre execute");
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
                    int id = 0;
                    Statement st = con.createStatement();
                    result = "Database connection success\n";
                    ArrayList<Integer> id_lekow = new ArrayList<>();
                    for (int i = 0; i < ScanActivity.values.length; i++) {
                        ResultSet rs = st.executeQuery("SELECT id from leki.leki where nazwa ='" + ScanActivity.values[i] + "'");
                        while (rs.next()) {
                            id_lekow.add(rs.getInt(1));
                        }
                    }
                    for (Integer id_lek : id_lekow) {
                        st.executeUpdate("INSERT INTO `leki`.`pacjent_leki` (leki_id,pacjent_id) VALUES (" + id_lek + "," + patient_id + ");");
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
        Log.i(TAG, "post execute");
    }


}
