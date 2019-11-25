package pl.milosz.medbase.Meds;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import pl.milosz.medbase.R;
/**
 * Aktywność odpowiadająca za wyświetlenie leków, które zostały usunięte z listy przyjmowanych leków
 * Leki znajdujące się w tej liście służa jako prosta historia medyczna
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class BrowseMedsActivity extends AppCompatActivity {
    public static ArrayList<String> deletedMedicationList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_meds);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView browselistView = findViewById(R.id.browseListView);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, deletedMedicationList);
        browselistView.setAdapter(adapter);
    }
}
