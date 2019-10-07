package pl.milosz.medbase.DB;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.milosz.medbase.R;

public class DB extends AppCompatActivity {
    static final String url = "jdbc:mysql://localhost:3306/student";
    //static final String url = "jdbc:mysql://remotemysql.com/MyET9xK68t";
    static final String user = "hbstudent";
    //static final String user = "MyET9xK68t";
    //static final String pass = "LQNoYfHBn4";
    static final String pass = "hbstudent";
    //public static List<objClass> objList;
    static ArrayList<Object> lista = new ArrayList<Object>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_db);
            //new Download(this, url).execute(); //async task for getting data from db
           // Log.i("Guwno",lista.toString());
            try {
                Connection con = DriverManager.getConnection(url, user, pass);
                Log.i("guwno",con.toString());
                if(con!=null){
                    Log.i("guwno","cos tam sie udalo");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
