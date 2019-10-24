package pl.milosz.medbase.DB;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pl.milosz.medbase.R;

public class DB extends AppCompatActivity {
    static final String url = "jdbc:mysql://localhost:3306/hb-05-many-to-many";//?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";//";//?useSSL=false&amp;serverTimezone=UTC";//&autoReconnect=true";//&failOverReadOnly=false&maxReconnects=10";
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
            new Download(this).execute(); //async task for getting data from db
        }
}
