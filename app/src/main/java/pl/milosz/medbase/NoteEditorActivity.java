package pl.milosz.medbase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText=findViewById(R.id.noteEditText);
        Intent intent=getIntent();
        noteId=intent.getIntExtra("noteId",-1);

        if(noteId!=-1) {
            editText.setText(NotesActivity.notes.get(noteId));
        } else {
            NotesActivity.notes.add("");
            noteId = NotesActivity.notes.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NotesActivity.notes.set(noteId,String.valueOf(s));
                NotesActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("pl.milosz.medbase", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(NotesActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
