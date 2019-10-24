package pl.milosz.medbase.Meds;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pl.milosz.medbase.R;

import static pl.milosz.medbase.Meds.MedsActivity.medicationArrayList;

public class MedDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int index = getIntent().getExtras().getInt("index",-1);
        setContentView(R.layout.activity_med_details);
        TextView nameText=findViewById(R.id.nameTextView);
        TextView intakeText=findViewById(R.id.intakeTextView);
        TextView descriptionText=findViewById(R.id.descriptionTextView);
        nameText.setText(medicationArrayList.get(index).getName());
        intakeText.setText(medicationArrayList.get(index).getDose());
        descriptionText.setText(medicationArrayList.get(index).getManufacturer());
    }
}
