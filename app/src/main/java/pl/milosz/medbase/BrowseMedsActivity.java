package pl.milosz.medbase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class BrowseMedsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_meds);

        ListView browselistView = findViewById(R.id.browseListView);

        //przykladowe dane do lekow
        Medication ranigastMax = new Medication("Ranigast Max", "150mg", "Polpharma");
        Medication acodin = new Medication("Acodin", "15mg", "Sanofi-Aventis");
        Medication rutinacea = new Medication("Rutinacea", "240mg", "Aflofarm");
        Medication paracetamol = new Medication("Paracetamol", "500mg", "Hasco-Lek");
        Medication nospa = new Medication("No-Spa", "40mg", "Sanofi-Aventis");

        ArrayList<Medication> medicationArrayList=new ArrayList<>();
        medicationArrayList.add(ranigastMax);
        medicationArrayList.add(acodin);
        medicationArrayList.add(rutinacea);
        medicationArrayList.add(paracetamol);
        medicationArrayList.add(nospa);

        MedicationListAdapter adapter= new MedicationListAdapter(this, R.layout.adapter_view, medicationArrayList);
        browselistView.setAdapter(adapter);
    }
}
