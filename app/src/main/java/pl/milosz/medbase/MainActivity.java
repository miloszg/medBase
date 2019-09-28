package pl.milosz.medbase;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import pl.milosz.medbase.Alerts.AlertsActivity;
import pl.milosz.medbase.CalendarView.CalendarActivity;
import pl.milosz.medbase.NotesView.NotesActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button_med:
                Intent intent_med = new Intent(this, MedsActivity.class);
                startActivity(intent_med);
                break;
            case R.id.button_browse_meds:
                Intent intent_browse = new Intent(this, BrowseMedsActivity.class);
                startActivity(intent_browse);
                break;
            case R.id.button_scan_qr:
                Intent intent_scan = new Intent(this, ScanActivity.class);
                startActivity(intent_scan);
                break;
            case R.id.button_alerts:
                Intent intent_alerts = new Intent(this, AlertsActivity.class);
                startActivity(intent_alerts);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_meds:
                Log.d(TAG, "onNavigationItemSelected: clicked on meds");
                Intent intent_med = new Intent(this, MedsActivity.class);
                startActivity(intent_med);
                break;
            case R.id.nav_calendar:
                Log.d(TAG, "onNavigationItemSelected: clicked on calendar");
                Intent intent_cal = new Intent(this, CalendarActivity.class);
                startActivity(intent_cal);
                return true;
            case R.id.nav_settings:
                Log.d(TAG, "onNavigationItemSelected: clicked on settings");
                Intent intent_set = new Intent(this, SettingsActivity.class);
                startActivity(intent_set);
                return true;
            case R.id.nav_notes:
                Log.d(TAG, "onNavigationItemSelected: clicked on notes");
                Intent intent_note = new Intent(this, NotesActivity.class);
                startActivity(intent_note);
                return true;
            default:
                return false;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}


