package pl.milosz.medbase;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import pl.milosz.medbase.Alerts.AlertsActivity;
import pl.milosz.medbase.CalendarView.CalendarActivity;
import pl.milosz.medbase.Meds.BrowseMedsActivity;
import pl.milosz.medbase.Meds.MedsActivity;
import pl.milosz.medbase.NotesView.NotesActivity;

import static pl.milosz.medbase.LoginActivity.offlineMode;

/**
 * Aktywność odpowiadająca za wyświetlenie funkcjonalności aplikacji
 * W samej aktywności znajdują się przyciski, które przekierują do opisanych części projektu
 * W pasku bocznym menu umiejscowione są pozostałe opcje wyboru
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
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
        View header = navigationView.getHeaderView(0);

        TextView navNameTextView = header.findViewById(R.id.navName);
        TextView navMailTextView = header.findViewById(R.id.navMail);
        if (!offlineMode) {
            navNameTextView.setText(LoginActivity.username);
            navMailTextView.setText("mail@mail.com");
        } else {
            navNameTextView.setText("Tryb Offline");
            navMailTextView.setText(" ");
            View viewQR = findViewById(R.id.cardQR);
            viewQR.setEnabled(false);

            View viewCode = findViewById(R.id.cardCode);
            viewCode.setEnabled(false);
            ImageView codeImg = findViewById(R.id.codeImage);
            TextView codeTxt = findViewById(R.id.codeText);
            codeImg.setColorFilter(Color.LTGRAY);
            codeTxt.setTextColor(Color.LTGRAY);
            ImageView qrImg = findViewById(R.id.qrImage);
            TextView qrTxt = findViewById(R.id.qrText);
            qrImg.setColorFilter(Color.LTGRAY);
            qrTxt.setTextColor(Color.LTGRAY);
        }


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardMeds:
                Intent intent_med = new Intent(this, MedsActivity.class);
                startActivity(intent_med);
                break;
            case R.id.cardCode:
                Intent intent_browse = new Intent(this, CodeActivity.class);
                startActivity(intent_browse);
                break;
            case R.id.cardQR:
                Intent intent_scan = new Intent(this, ScanActivity.class);
                startActivity(intent_scan);
                break;
            case R.id.cardAlerts:
                Intent intent_alerts = new Intent(this, AlertsActivity.class);
                startActivity(intent_alerts);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_meds:
                Log.d(TAG, "onNavigationItemSelected: clicked on meds");
                Intent intent_browse = new Intent(this, BrowseMedsActivity.class);
                startActivity(intent_browse);
                break;
            case R.id.nav_calendar:
                Log.d(TAG, "onNavigationItemSelected: clicked on calendar");
                Intent intent_cal = new Intent(this, CalendarActivity.class);
                startActivity(intent_cal);
                return true;
            case R.id.nav_notes:
                Log.d(TAG, "onNavigationItemSelected: clicked on notes");
                Intent intent_note = new Intent(this, NotesActivity.class);
                startActivity(intent_note);
                return true;
            case R.id.nav_settings:
                Log.d(TAG, "onNavigationItemSelected: clicked on settings");
                Intent intent_settings = new Intent();
                intent_settings.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent_settings.setData(uri);
                startActivity(intent_settings);
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

        }
    }
}


