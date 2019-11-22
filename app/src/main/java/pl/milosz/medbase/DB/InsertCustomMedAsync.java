package pl.milosz.medbase.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import pl.milosz.medbase.Meds.CustomMedActivity;

import static pl.milosz.medbase.LoginActivity.offlineMode;

public class InsertCustomMedAsync extends AsyncTask<Void, Void, String> {
    Context context;
    String result;
    public static String twoj_stary = " ";
    public static Connection con;
    String nameMed;
    String instakeMed;
    String descriptionMed;

    public InsertCustomMedAsync(Context context) {
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
        nameMed= CustomMedActivity.name;
        instakeMed= CustomMedActivity.intake;
        descriptionMed= CustomMedActivity.description;
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
                    String statement="INSERT INTO `leki`.`leki` (nazwa,informacje,dawkowanie) VALUES ('Stoperan' , 'kapsulka twarda' , '2 tabletki na dobe');";
                    //st.executeUpdate("INSERT INTO `leki`.`leki` (nazwa,info,dawkowanie) VALUES ("+nameMed+", "+descriptionMed+", " +instakeMed+");");
                    st.executeUpdate(statement);
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
