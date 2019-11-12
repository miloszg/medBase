package pl.milosz.medbase;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pl.milosz.medbase.Meds.Medication;
import pl.milosz.medbase.Meds.MedsListAdapter;

public class BrowseMedsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_meds);

        ListView browselistView = findViewById(R.id.browseListView);

        //przykladowe dane do lekow
        Medication ranigastMax = new Medication("Ranigast Max", "150mg", "Polpharma",R.drawable.ic_meds);
        Medication acodin = new Medication("Acodin", "15mg", "Sanofi-Aventis",R.drawable.ic_meds);
        Medication rutinacea = new Medication("Rutinacea", "240mg", "Aflofarm",R.drawable.ic_meds);
        Medication paracetamol = new Medication("Paracetamol", "500mg", "Hasco-Lek",R.drawable.ic_meds);
        Medication nospa = new Medication("No-Spa", "40mg", "Sanofi-Aventis",R.drawable.ic_meds);

        ArrayList<Medication> medicationArrayList=new ArrayList<>();
        medicationArrayList.add(ranigastMax);
        medicationArrayList.add(acodin);
        medicationArrayList.add(rutinacea);
        medicationArrayList.add(paracetamol);
        medicationArrayList.add(nospa);

        MedsListAdapter adapter= new MedsListAdapter(this, R.layout.meds_adapter_view, medicationArrayList);
        browselistView.setAdapter(adapter);
    }
}
