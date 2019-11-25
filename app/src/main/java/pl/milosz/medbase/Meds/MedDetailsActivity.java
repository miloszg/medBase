package pl.milosz.medbase.Meds;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import pl.milosz.medbase.Alerts.DatePickerFragment;
import pl.milosz.medbase.Alerts.NotificationReceiver;
import pl.milosz.medbase.Alerts.TimePickerFragment;
import pl.milosz.medbase.DB.DeleteMed;
import pl.milosz.medbase.DB.DownloadMeds;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.LoginActivity.offlineMode;
import static pl.milosz.medbase.Meds.BrowseMedsActivity.deletedMedicationList;

/**
 * Aktywność, w której wyświetlane są informacje o indywidualnym leku
 * Do tej aktywności użytkownik przechodzi poprzez wybranie opcji z listy dostępnej w MedsActivity
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class MedDetailsActivity extends AppCompatActivity implements android.app.TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private Calendar c;
    AlarmManager alarmManagerMeds;
    int option = 0;
    int index;
    int frequency = 0;
    TextView startDateText;
    TextView endDateText;
    TextView frequencyText;
    Button alarmButton;
    PendingIntent pendingIntents;
    Intent intentMed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_med_details);
        index = getIntent().getExtras().getInt("index", -1);
        TextView nameText = findViewById(R.id.nameTextView);
        TextView intakeText = findViewById(R.id.intakeTextView);
        TextView descriptionText = findViewById(R.id.descriptionTextView);
        nameText.setText(DownloadMeds.dbMeds.get(index).getName());
        intakeText.setText(DownloadMeds.dbMeds.get(index).getDose());
        descriptionText.setText(DownloadMeds.dbMeds.get(index).getManufacturer());

        alarmManagerMeds = (AlarmManager) getSystemService(ALARM_SERVICE);

        startDateText = findViewById(R.id.startDateText);
        endDateText = findViewById(R.id.endDateText);
        frequencyText = findViewById(R.id.frequencyText);

        startDateText.setOnClickListener(v -> {
            option = 1;
            sendOnDate(v);
        });

        endDateText.setOnClickListener(v -> {
            option = 2;
            sendOnDate(v);
        });

        alarmButton = findViewById(R.id.medDetailsAlarm);
        alarmButton.setEnabled(false);
        alarmButton.setOnClickListener(view -> {
            sendOnTime(view);
        });

        frequencyText.setOnClickListener(v -> frequencyDialog());
        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> dialogDeleteMed());
    }

    public void sendOnTime(View view) {
        Toast.makeText(this, "Wybierz czas pierwszego powiadomienia", Toast.LENGTH_SHORT).show();
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void sendOnDate(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        intentMed = new Intent(this, NotificationReceiver.class);
        intentMed.putExtra("text", DownloadMeds.dbMeds.get(index).getName());
        intentMed.putExtra("channel", "three");
        intentMed.putExtra("icon", DownloadMeds.dbMeds.get(index).getDrawable());
        pendingIntents = PendingIntent.getBroadcast(this, 3, intentMed, PendingIntent.FLAG_UPDATE_CURRENT);
        if (frequency == 1) {
            alarmManagerMeds.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntents);
        }
        if (frequency == 2) {
            alarmManagerMeds.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntents);
        }
        if (frequency == 3) {
            alarmManagerMeds.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 6 * 3600 * 1000, pendingIntents);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (option == 1) {
            startDateText.setText("Data rozpoczęcia: " + dayOfMonth + "-" + month + "-" + year);
            c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        } else if (option == 2) {
            endDateText.setText("Data zakończenia: " + dayOfMonth + "-" + month + "-" + year);
        }
    }

    public void dialogDeleteMed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Po usunięciu lek będzie wyświetlany w opcji z menu 'Zakończone leki'")
                .setTitle("Czy na pewno chcesz usunąć lek?")
                .setPositiveButton("Tak", (dialog, id) -> {
                    deletedMedicationList.add(DownloadMeds.dbMeds.get(index).getName());

                    DownloadMeds.dbMeds.remove(index);
                    if (pendingIntents != null) {
                        pendingIntents = PendingIntent.getBroadcast(this, 3, intentMed, PendingIntent.FLAG_UPDATE_CURRENT);
                        alarmManagerMeds.cancel(pendingIntents);
                    }
                    if (offlineMode == false) {
                        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = cm.getActiveNetworkInfo();
                        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                            new DeleteMed(getApplicationContext()).execute();
                        }
                    }
                    Intent medIntent = new Intent(getApplicationContext(), MedsActivity.class);
                    startActivity(medIntent);
                })
                .setNegativeButton("Nie", (dialog, id) -> {
                });
        builder.create();
        builder.show();
    }

    public void frequencyDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.frequency_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        builder.create();
        builder.show();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.onceCheckbox:
                if (checked) {
                    frequency = 1;
                    frequencyText.setText("Raz na dobę");
                    Toast.makeText(this, "Dostaniesz powiadomienie raz na 24h", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.twiceCheckbox:
                if (checked) {
                    frequency = 2;
                    frequencyText.setText("Dwa razy na dobę");
                    Toast.makeText(this, "Dostaniesz powiadomienie co 12h", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.triceCheckbox:
                if (checked) {
                    frequency = 3;
                    frequencyText.setText("Trzy razy na dobę");
                    Toast.makeText(this, "Dostaniesz powiadomienie co 6h", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        alarmButton.setEnabled(true);
    }
}
