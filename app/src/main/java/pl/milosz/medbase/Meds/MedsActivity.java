package pl.milosz.medbase.Meds;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pl.milosz.medbase.DB.DownloadMeds;
import pl.milosz.medbase.MainActivity;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.DB.DownloadMeds.dbMeds;

/**
 * Aktywność, w której wyświetlana jest lista zawierająca leki przyjmowane przez użytkownika
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class MedsActivity extends AppCompatActivity {
    public static MedsListAdapter adapter;
    private static final String TAG = "MedsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meds);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            new DownloadMeds(getApplicationContext()).execute();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent customMedIntent = new Intent(getApplicationContext(), CustomMedActivity.class);
            startActivity(customMedIntent);
        });


        ListView mlistView = findViewById(R.id.medListview);
        adapter = new MedsListAdapter(this, R.layout.meds_adapter_view, dbMeds);
        mlistView.setAdapter(adapter);

        if(adapter!=null){
            try {
                Thread.sleep(500);
                this.runOnUiThread(() -> adapter.notifyDataSetChanged());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        mlistView.setOnItemClickListener((parent, view, position, id) -> {
            Intent medsIntent = new Intent(getApplicationContext(), MedDetailsActivity.class);
            medsIntent.putExtra("index", position);
            startActivity(medsIntent);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
