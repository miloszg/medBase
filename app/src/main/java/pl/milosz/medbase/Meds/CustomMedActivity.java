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

public class CustomMedActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText intakeEditText;
    EditText descriptionEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_med);
        nameEditText=findViewById(R.id.customMedNameEditText);
        intakeEditText=findViewById(R.id.customMedIntakeEditText);
        descriptionEditText=findViewById(R.id.customMedDescriptionEditText);
        Button customMedButton = findViewById(R.id.addCustomMedButton);
        customMedButton.setOnClickListener(v -> {
            String name=nameEditText.getText().toString();
            String intake=intakeEditText.getText().toString();
            String description=descriptionEditText.getText().toString();
            Log.i("guwnoo: ",name+" : "+intake+" : " + description);
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
