package pl.milosz.medbase;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MedsActivity extends AppCompatActivity {
    private static final String TAG = "MedsActivity";

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

        ArrayList<Medication> medicationArrayList=new ArrayList<>();
        medicationArrayList.add(ranigastMax);
        medicationArrayList.add(acodin);
        medicationArrayList.add(rutinacea);

        MedicationListAdapter adapter= new MedicationListAdapter(this, R.layout.adapter_view, medicationArrayList);
        mlistView.setAdapter(adapter);
    }

}
