package pl.milosz.medbase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import pl.milosz.medbase.DB.GetAccountsInfo;
import pl.milosz.medbase.DB.User;

import static pl.milosz.medbase.DB.GetAccountsInfo.users;

/**
 * Aktywność odpowiadająca za okno logowania do aplikacji
 * W przypadku braku połączeia z internetem użytkownik może przejść dalej w trybie offline
 *
 * @author Miłosz Gustawski
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;
    Button buttonSignUp;
    public static boolean offlineMode = false;
    ConnectivityManager cm;
    NetworkInfo netInfo;
    static String username;
    String password;
    static public int patient_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null) {
            offlineMode();
        }
        if (netInfo != null && netInfo.isConnectedOrConnecting() && offlineMode == false) {
            new GetAccountsInfo(getApplicationContext()).execute();
        }
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        buttonSignUp = findViewById(R.id.signUpButton);
        buttonSignUp.setOnClickListener(v -> {
            username = usernameEditText.getText().toString();
            password = passwordEditText.getText().toString();
            int index = 0;
            if (username.equals("") || password.equals("")) {
                Toast.makeText(LoginActivity.this, "Prosze podać nazwę użytkownika i hasło!", Toast.LENGTH_SHORT).show();
            }
            boolean loggedIn = false;
            if (users == null) {
                Toast.makeText(this, "Wystąpił błąd z bazą danych! Kontynuuj w trybie offline!", Toast.LENGTH_SHORT).show();
            }
            for (User user : users) {
                if (username.equals(users.get(index).getUsername()) && password.equals(users.get(index).getPassword())) {
                    patient_id = users.get(index).getId();
                    loggedIn = true;
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                }
                index++;
            }
            if (!loggedIn) {
                Toast.makeText(this, "Prosze podać prawidłowe dane!", Toast.LENGTH_SHORT).show();

            }
        });
        Button offlineButton = findViewById(R.id.offlineModeButton);
        offlineButton.setOnClickListener(v ->
                offlineMode()
        );

    }

    public void offlineMode() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("W trybie offline nie będziesz mógł skorzystać ze wszystkich funkcji aplikacji.")
                .setTitle("Czy chcesz kontynuować w trybie offline?")
                .setPositiveButton("Tak", (dialog, id) -> {
                    offlineMode = true;
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                })
                .setNegativeButton("Nie", (dialog, id) -> {
                });
        builder.create();
        builder.show();
    }
}

