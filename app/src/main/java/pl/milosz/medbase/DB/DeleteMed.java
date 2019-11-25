package pl.milosz.medbase.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pl.milosz.medbase.Meds.CustomMedActivity;

import static pl.milosz.medbase.LoginActivity.offlineMode;
import static pl.milosz.medbase.LoginActivity.patient_id;
/**
 * Asynchroniczna akcja odpowiadająca za usunięcie leku z bazy danych przyjmowanych leków przez pacjenta
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class DeleteMed extends AsyncTask<Void, Void, String> {
    Context context;
    public static Connection con;
    private static final String TAG = "DeleteMed";

    public DeleteMed(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {
        Log.i(TAG, "Pre execute");
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
                    int id = 0;
                    ResultSet rs = st.executeQuery("SELECT id from leki.leki where nazwa ='" + CustomMedActivity.name + "'");
                    while (rs.next()) {
                        id = (rs.getInt(1));
                    }
                    st.executeUpdate("DELETE FROM `leki`.`pacjent_leki` WHERE pacjent_id=" + patient_id + " AND leki_id=" + id + ");");
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
