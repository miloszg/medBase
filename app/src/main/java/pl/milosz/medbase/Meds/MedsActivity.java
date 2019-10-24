package pl.milosz.medbase.Meds;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import pl.milosz.medbase.R;

public class MedsActivity extends AppCompatActivity {
    private static final String TAG = "MedsActivity";
    public static ArrayList<Medication> medicationArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meds);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView mlistView = findViewById(R.id.medListview);

        //przykladowe dane do lekow
        Medication ranigastMax = new Medication("Ranigast Max", "150mg", "Polpharma");
        Medication acodin = new Medication("Acodin", "15mg", "Sanofi-Aventis");
        Medication rutinacea = new Medication("Rutinacea", "240mg", "Aflofarm");

        //ArrayList<Medication> medicationArrayList=new ArrayList<>();
        medicationArrayList.add(ranigastMax);
        medicationArrayList.add(acodin);
        medicationArrayList.add(rutinacea);

        MedicationListAdapter adapter= new MedicationListAdapter(this, R.layout.adapter_view, medicationArrayList);
        mlistView.setAdapter(adapter);

        mlistView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(MedsActivity.this, "clicked: "+medicationArrayList.get(position).getName(), Toast.LENGTH_SHORT).show();
            Intent medsIntent = new Intent(getApplicationContext(), MedDetailsActivity.class);
            medsIntent.putExtra("index",position);
            startActivity(medsIntent);
        });
    }

}
