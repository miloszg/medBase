package pl.milosz.medbase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import pl.milosz.medbase.DB.InsertPatientCode;

import static pl.milosz.medbase.LoginActivity.offlineMode;

public class CodeActivity extends AppCompatActivity {
    public static int fourDigit = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Random r = new Random();
        fourDigit = 1000 + r.nextInt(9000);
        System.out.println(fourDigit);
        TextView textView = findViewById(R.id.codeTextView);
        textView.setText(String.valueOf(fourDigit));

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting() && offlineMode==false) {
            new InsertPatientCode(getApplicationContext()).execute();
            Toast.makeText(this, "Dodano kod", Toast.LENGTH_SHORT).show();
        }
        Button button = findViewById(R.id.generateCodeButton);
        button.setOnClickListener(v -> {
            fourDigit = 1000 + r.nextInt(9000);
            textView.setText(String.valueOf(fourDigit));
            if (netInfo != null && netInfo.isConnectedOrConnecting() && offlineMode==false) {
                new InsertPatientCode(getApplicationContext()).execute();
                Toast.makeText(this, "Dodano kod", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
