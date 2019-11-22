package pl.milosz.medbase.Meds;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import pl.milosz.medbase.DB.Download;
import pl.milosz.medbase.MainActivity;
import pl.milosz.medbase.R;

public class MedsActivity extends AppCompatActivity {
    public static MedsListAdapter adapter;
    private static final String TAG = "MedsActivity";
//    public static ArrayList<Medication> medicationArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            new Download(getApplicationContext()).execute();
        }
        setContentView(R.layout.activity_meds);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(Download.dbMeds==null){
            Download.dbMeds=new ArrayList<>();
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent customMedIntent = new Intent(getApplicationContext(),CustomMedActivity.class);
            startActivity(customMedIntent);
        });

            ListView mlistView = findViewById(R.id.medListview);
            MedsListAdapter adapter=new MedsListAdapter(this, R.layout.meds_adapter_view, Download.dbMeds);
            adapter.notifyDataSetChanged();
            mlistView.setAdapter(adapter);

        mlistView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(MedsActivity.this, "clicked: "+Download.dbMeds.get(position).getName(), Toast.LENGTH_SHORT).show();
            Intent medsIntent = new Intent(getApplicationContext(), MedDetailsActivity.class);
            medsIntent.putExtra("index",position);
            startActivity(medsIntent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    public void generateData(){
//        Medication ranigastMax = new Medication("Ranigast Max", "150mg", "Polpharma",R.drawable.pill);
//        Medication acodin = new Medication("Acodin", "15mg", "Sanofi-Aventis",R.drawable.syringe);
//        Medication rutinacea = new Medication("Rutinacea", "240mg", "Aflofarm",R.drawable.round);
//        Medication herbapol = new Medication("Herbapol syrop", "240mg", "syropol",R.drawable.syrop);
//
//        medicationArrayList.add(ranigastMax);
//        medicationArrayList.add(acodin);
//        medicationArrayList.add(rutinacea);
//        medicationArrayList.add(herbapol);
//    }
}
