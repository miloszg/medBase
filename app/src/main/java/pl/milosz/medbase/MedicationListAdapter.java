package pl.milosz.medbase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class MedicationListAdapter extends ArrayAdapter<Medication> {
    private static final String TAG = "MedicationListAdapter";
    private Context mContext;
    private int mResource;

    public MedicationListAdapter(Context context, int resource, ArrayList<Medication> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name=getItem(position).getName();
        String dose=getItem(position).getDose();
        String manufacturer=getItem(position).getManufacturer();

        Medication medication=new Medication(name,dose,manufacturer);
        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);
        TextView textName=convertView.findViewById(R.id.textView1);
        TextView textDose=convertView.findViewById(R.id.textView2);
        TextView textManufacturer=convertView.findViewById(R.id.textView3);
        textName.setText(name);
        textDose.setText(dose);
        textManufacturer.setText(manufacturer);

        return convertView;
    }
}
