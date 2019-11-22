package pl.milosz.medbase.Meds;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
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
import pl.milosz.medbase.DB.Download;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.BrowseMedsActivity.deletedMedicationList;


public class MedDetailsActivity extends AppCompatActivity implements android.app.TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    private Calendar c;
    AlarmManager alarmManagerMeds;
    int option=0;
    int index;
    int frequency=0;
    TextView startDateText;
    TextView endDateText;
    TextView frequencyText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_med_details);
        index = getIntent().getExtras().getInt("index",-1);
        TextView nameText=findViewById(R.id.nameTextView);
        TextView intakeText=findViewById(R.id.intakeTextView);
        TextView descriptionText=findViewById(R.id.descriptionTextView);
        nameText.setText(Download.dbMeds.get(index).getName());
        intakeText.setText(Download.dbMeds.get(index).getDose());
        descriptionText.setText(Download.dbMeds.get(index).getManufacturer());

        alarmManagerMeds=(AlarmManager) getSystemService(ALARM_SERVICE);

        startDateText = findViewById(R.id.startDateText);
        endDateText = findViewById(R.id.endDateText);
        frequencyText = findViewById(R.id.frequencyText);

        startDateText.setOnClickListener(v -> {
            option=1;
            sendOnDate(v);
        });

        endDateText.setOnClickListener(v -> {
            option=2;
            sendOnDate(v);
        });

        Button alarmButton = findViewById(R.id.medDetailsAlarm);
        alarmButton.setOnClickListener(view -> {
                sendOnTime(view);
        });

        frequencyText.setOnClickListener(v -> frequencyDialog());
        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> dialogDeleteMed());
    }

    public void sendOnTime(View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    public void sendOnDate(View view) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(this, "Wybierz czas pierwszego powiadomienia", Toast.LENGTH_SHORT).show();

        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        Intent intentMed = new Intent(this, NotificationReceiver.class);
        intentMed.putExtra("text",Download.dbMeds.get(index).getName());
        intentMed.putExtra("channel","three");
        intentMed.putExtra("icon",Download.dbMeds.get(index).getDrawable());
        final PendingIntent pendingIntents; pendingIntents = PendingIntent.getBroadcast(this, 3, intentMed, 0);
        if(frequency==1){
            alarmManagerMeds.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntents);
        } if(frequency==2){
            alarmManagerMeds.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),AlarmManager.INTERVAL_HALF_DAY,pendingIntents);
        } if(frequency==3){
            alarmManagerMeds.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),100,pendingIntents); //co godzine 3600*1000
            //alarmManagerMeds.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),6*3600*1000,pendingIntents); //co godzine 3600*1000
        }
        //alarmManagerMeds.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntents);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if(option==1){
            startDateText.setText("Data rozpoczęcia: "+dayOfMonth+"-"+ month +"-"+year);
            c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        } else if (option==2) {
            endDateText.setText("Data zakończenia: " + dayOfMonth + "-" + month + "-" + year);
        }
    }

    public void dialogDeleteMed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Po usunięciu lek będzie wyświetlany w opcji z menu 'Zakończone leki'")
                .setTitle("Czy na pewno chcesz usunąć lek?")
                .setPositiveButton("Tak", (dialog, id) -> {
                    deletedMedicationList.add(Download.dbMeds.get(index));
                    Download.dbMeds.remove(index);
                    Intent medIntent = new Intent(getApplicationContext(), MedsActivity.class);
                    startActivity(medIntent);
                })
                .setNegativeButton("Nie", (dialog, id) -> {
                });
        builder.create();
        builder.show();
    }

    public void frequencyDialog(){
        LayoutInflater inflater= this.getLayoutInflater();
        View view = inflater.inflate(R.layout.frequency_dialog,null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        builder.create();
        builder.show();
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.onceCheckbox:
                if (checked){
                    frequency=1;
                    frequencyText.setText("Raz na dobę");
                    Toast.makeText(this, "Dostaniesz powiadomienie raz na 24h", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.twiceCheckbox:
                if (checked){
                    frequency=2;
                    frequencyText.setText("Dwa razy na dobę");
                    Toast.makeText(this, "Dostaniesz powiadomienie co 12h", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.triceCheckbox:
                if(checked){
                    frequency=3;
                    frequencyText.setText("Trzy razy na dobę");
                    Toast.makeText(this, "Dostaniesz powiadomienie co 6h", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
