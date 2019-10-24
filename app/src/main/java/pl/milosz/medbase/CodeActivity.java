package pl.milosz.medbase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CodeActivity extends AppCompatActivity {
    int fourDigit=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        Random r = new Random();
        fourDigit= 1000 + r.nextInt(10000);
        System.out.println(fourDigit);
        TextView textView=findViewById(R.id.codeTextView);
        textView.setText(String.valueOf(fourDigit));

        Button button = findViewById(R.id.generateCodeButton);
        button.setOnClickListener(v -> {
            fourDigit = 1000 + r.nextInt(10000);
            textView.setText(String.valueOf(fourDigit));
        });
    }
}
