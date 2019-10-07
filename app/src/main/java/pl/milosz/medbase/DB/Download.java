package pl.milosz.medbase.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.sql.DriverManager;
import java.sql.SQLException;

import static pl.milosz.medbase.DB.DB.pass;
import static pl.milosz.medbase.DB.DB.user;

public class Download extends AsyncTask<Void, Void, String> {
    Context context;
    private String url;

    public Download(Context context, String url) {
        this.context = context;
        this.url = url;
    }

    protected void onPreExecute() {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show();
    }

    protected String doInBackground(Void... params) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(url, user, pass);
            Log.i("guwno",con.toString());
            if(con!=null)
            {
                Log.i("guwno","cos tam sie udalo");
            }
            java.sql.Statement st = con.createStatement();
           // java.sql.ResultSet student = st.executeQuery("SELECT * FROM `hb-01-one-to-one-uni`.instructor");
            java.sql.ResultSet rs = st.executeQuery("select * "+
                    "FROM `MyET9xK68t`.`leki`\n" +
                   "LIMIT 10;");

            while (rs.next()) {
                String field= rs.getString("first_name");
                DB.lista.add(field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Complete";
    }

    protected void onPostExecute(String result) {
        if (result.equals("Complete")) {
            Toast.makeText(context, "Koniec ładowania", Toast.LENGTH_SHORT).show();
        }
    }
}