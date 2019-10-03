package pl.milosz.medbase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class ScanActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    SwitchCompat sw,sw1,sw2,sw3,sw4,sw5;
    Button buttonKupa;
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        linearLayout = findViewById(R.id.linearScanLayout);
        buttonKupa=findViewById(R.id.kupaButton);
        buttonKupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index){
                    case 0:
                        dodajSwitch(sw);
                        break;
                    case 1:
                        dodajSwitch(sw1);
                        break;
                    case 2:
                        dodajSwitch(sw2);
                        break;
                    case 3:
                        dodajSwitch(sw3);
                        break;
                    case 4:
                        dodajSwitch(sw4);
                        break;
                }
            }
        });

    }
    public void dodajSwitch(SwitchCompat sw){
        sw = new SwitchCompat(this);
        sw.setId(index);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 30, 30, 30);
        sw.setLayoutParams(layoutParams);
        sw.setText("OFF");
        final SwitchCompat finalSw = sw;
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String msg = isChecked ? "ON" : "OFF";
                Toast.makeText(ScanActivity.this, String.valueOf(finalSw.getId()), Toast.LENGTH_SHORT).show();
            }
        });
        if (linearLayout != null) {
            linearLayout.addView(sw);
        }
        Log.i("KUPADUPA",String.valueOf(sw.getId()));
        index++;

    }
}
