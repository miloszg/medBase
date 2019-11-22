package pl.milosz.medbase.Meds;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import pl.milosz.medbase.DB.Download;
import pl.milosz.medbase.DB.InsertCustomMedAsync;
import pl.milosz.medbase.MainActivity;
import pl.milosz.medbase.R;

public class CustomMedActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText intakeEditText;
    private EditText descriptionEditText;
    public static String name = "";
    public static String intake = "";
    public static String description = "";
    public static int drawable = R.drawable.pill;
    boolean close=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_med);

        nameEditText = findViewById(R.id.customMedNameEditText);
        intakeEditText = findViewById(R.id.customMedIntakeEditText);
        descriptionEditText = findViewById(R.id.customMedDescriptionEditText);

        Button addIconButton = findViewById(R.id.addIconButton);
        addIconButton.setOnClickListener(v ->
          addIcon()
        );

        Button customMedButton = findViewById(R.id.addCustomMedButton);
        customMedButton.setOnClickListener(v -> {
            name = nameEditText.getText().toString();
            intake = intakeEditText.getText().toString();
            description = descriptionEditText.getText().toString();
            Log.i("guwnoo: ", name + " : " + intake + " : " + description);

            Medication customMed = new Medication(name, intake, description, drawable);
            Download.dbMeds.add(customMed);

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                new InsertCustomMedAsync(getApplicationContext()).execute();
            }
            Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainIntent);
        });
    }

    public void addIcon(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        View icon_dialog=inflater.inflate(R.layout.icon_dialog,null);
        builder.setView(icon_dialog);

        builder.create();
        final AlertDialog dialog = builder.show();
        if(close){
            dialog.cancel();
        }
    }

    public void onClick(View view){
        Toast.makeText(this, String.valueOf(view.getTag()), Toast.LENGTH_SHORT).show();
        String tag=String.valueOf(view.getTag());
        if(tag.equals("round")) {
            drawable=R.drawable.round;
        } else if(tag.equals("syringe")) {
            drawable=R.drawable.syringe;
        } else if(tag.equals("syrop")) {
            drawable=R.drawable.syrop;
        } else {
            drawable = R.drawable.pill;
        }
        close=true;
    }
}
