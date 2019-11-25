package pl.milosz.medbase.NotesView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

import pl.milosz.medbase.R;
/**
 * Aktywność za wyświetlenie notatek wprowadzonych przez użytkownika w liście
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class NotesActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView list = findViewById(R.id.notesListView);

        sharedPreferences = getApplicationContext().getSharedPreferences("pl.milosz.medbase", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet) sharedPreferences.getStringSet("notes", null);
        if (set == null) {
            notes.add("Kliknij aby dodać notatkę");
        } else {
            notes = new ArrayList(set);
        }


        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        list.setAdapter(arrayAdapter);

        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
            intent.putExtra("noteId", position);
            startActivity(intent);
        });
        list.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(NotesActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Czy jesteś pewien?")
                    .setMessage("Czy chcesz usunać tą notatkę?")
                    .setPositiveButton("Tak", (dialog, which) -> {
                        notes.remove(position);
                        arrayAdapter.notifyDataSetChanged();

                        HashSet<String> set1 = new HashSet<>(NotesActivity.notes);
                        sharedPreferences.edit().putStringSet("notes", set1).apply();
                    })
                    .setNegativeButton("Nie", null)
                    .show();

            return true;
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intentFAB = new Intent(getApplicationContext(), NoteEditorActivity.class);
            startActivity(intentFAB);
        });
    }
}
