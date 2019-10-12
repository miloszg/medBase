package pl.milosz.medbase.Alerts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import pl.milosz.medbase.R;

public class NotificationTextFragment extends DialogFragment {
    private EditText notificationEditText;
    private NotificationTextListener notificationTextListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.TextDialogTheme);
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);
       // view.getBackground().setColorFilter(Color.parseColor("#05ca7e"), PorterDuff.Mode.DARKEN);

        builder.setView(view)
//                .setTitle(" \n \n \n \n")
//                .setIcon(R.drawable.ic_text)
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String notificationText = notificationEditText.getText().toString();
                        if(notificationText.equals("")){
                            Toast.makeText(getContext(), "Prosze podać tekst powiadomienia", Toast.LENGTH_SHORT).show();
                        } else {
                            notificationTextListener.getText(notificationText);
                        }
                    }
                });
        notificationEditText=view.findViewById(R.id.notificationEditText);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            notificationTextListener= (NotificationTextListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement Listener");
        }
    }

    public interface NotificationTextListener {
        void getText(String notificationText);
    }
}
