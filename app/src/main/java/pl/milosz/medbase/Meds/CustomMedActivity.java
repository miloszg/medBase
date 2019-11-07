package pl.milosz.medbase.Meds;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import pl.milosz.medbase.DB.InsertCustomMedAsync;
import pl.milosz.medbase.MainActivity;
import pl.milosz.medbase.R;

import static pl.milosz.medbase.Meds.MedsActivity.medicationArrayList;

public class CustomMedActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText intakeEditText;
    private EditText descriptionEditText;
    public static String name="";
    public static String intake="";
    public static String description="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_med);
        nameEditText=findViewById(R.id.customMedNameEditText);
        intakeEditText=findViewById(R.id.customMedIntakeEditText);
        descriptionEditText=findViewById(R.id.customMedDescriptionEditText);
        Button customMedButton = findViewById(R.id.addCustomMedButton);
        customMedButton.setOnClickListener(v -> {
            name=nameEditText.getText().toString();
            intake=intakeEditText.getText().toString();
            description=descriptionEditText.getText().toString();
            Log.i("guwnoo: ",name+" : "+intake+" : " + description);

            Medication customMed = new Medication(name, intake, description);
            medicationArrayList.add(customMed);

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                new InsertCustomMedAsync(getApplicationContext()).execute();
            }
            Intent mainIntent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
        });
    }
}
