package pl.milosz.medbase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

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


