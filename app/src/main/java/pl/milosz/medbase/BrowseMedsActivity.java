package pl.milosz.medbase;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import pl.milosz.medbase.Meds.Medication;
import pl.milosz.medbase.Meds.MedsListAdapter;

public class BrowseMedsActivity extends AppCompatActivity {
    public static ArrayList<Medication> deletedMedicationList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_meds);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView browselistView = findViewById(R.id.browseListView);


        MedsListAdapter adapter= new MedsListAdapter(this, R.layout.meds_adapter_view, deletedMedicationList);
        browselistView.setAdapter(adapter);
    }
}
