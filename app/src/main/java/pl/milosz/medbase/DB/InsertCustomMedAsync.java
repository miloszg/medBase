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
 * Asynchroniczna akcja odpowiadająca za dodanie manualnie wprowadzonego leku do bazy danych
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class InsertCustomMedAsync extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static Connection con;
    String nameMed;
    String instakeMed;
    String descriptionMed;
    private static final String TAG = "InsertCustomMedAsync";

    public InsertCustomMedAsync(Context context) {
        this.context = context;

    }

    protected void onPreExecute() {
        Log.i(TAG, "pre execute");
    }


    protected String doInBackground(Void... params) {
        String url = "jdbc:mysql://192.168.0.129:3306/leki?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "admin";
        String pass = "admin";
        int id = 0;
        nameMed = CustomMedActivity.name;
        instakeMed = CustomMedActivity.intake;
        descriptionMed = CustomMedActivity.description;
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
                    String statement = "INSERT INTO `leki`.`leki` (nazwa,informacje,dawkowanie) VALUES ('" + nameMed + "' , '" + descriptionMed + "' , '" + descriptionMed + "');";
                    st.executeUpdate(statement);

                    //Mozliwe ze trzeba wait
                    ResultSet rs = st.executeQuery("SELECT id from leki.leki where nazwa ='" + nameMed + "'");
                    while (rs.next()) {
                        id = (rs.getInt(1));
                    }

                    st.executeUpdate("INSERT INTO `leki`.`pacjent_leki` (leki_id,pacjent_id) VALUES (" + id+ "," + patient_id + ");");

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
