package pl.milosz.medbase.Meds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;

import pl.milosz.medbase.R;

/**
 * Adapter umożliwiający niestandardowe wyświetlanie leku w liście
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class MedsListAdapter extends ArrayAdapter<Medication> {
    private Context medContext;
    private int res;

    MedsListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Medication> objects) {
        super(context, resource, objects);
        medContext = context;
        res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String medName = Objects.requireNonNull(getItem(position)).getName();
        String medDose = Objects.requireNonNull(getItem(position)).getDose();
        String medInfo = Objects.requireNonNull(getItem(position)).getManufacturer();
        int drawable = Objects.requireNonNull(getItem(position)).getDrawable();
        Medication med = new Medication(medName, medDose, medInfo, drawable);
        LayoutInflater inflater = LayoutInflater.from(medContext);
        convertView = inflater.inflate(res, parent, false);

        TextView nameText = convertView.findViewById(R.id.header1);
        TextView infoText = convertView.findViewById(R.id.header2);
        ImageView image = convertView.findViewById(R.id.medImageView);
        nameText.setText(medName);
        infoText.setText(medInfo);
        image.setImageResource(drawable);
        return convertView;
    }
}
