package pl.milosz.medbase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import pl.milosz.medbase.DB.GetAccountsInfo;
import pl.milosz.medbase.DB.User;

import static pl.milosz.medbase.DB.GetAccountsInfo.users;

public class LoginActivity extends AppCompatActivity {
    Boolean loginMode = true;
    TextView loginText;
    EditText usernameEditText;
    EditText passwordEditText;
    Button buttonSignUp;
    public static boolean offlineMode = false;
    ConnectivityManager cm;
    NetworkInfo netInfo;
    static String username;
    static String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean connected = false;

        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo= cm.getActiveNetworkInfo();
        if (netInfo == null) {
            offlineMode();
        }
        if (netInfo != null && netInfo.isConnectedOrConnecting() && offlineMode==false) {
            connected = true;
            new GetAccountsInfo(getApplicationContext()).execute();
        }
        Log.i("guwno połączenie: ", String.valueOf(connected));
        setContentView(R.layout.activity_login);
//        loginText = findViewById(R.id.loginText);
//        loginText.setOnClickListener(v -> {
//            if (loginMode) {
//                loginMode = false;
//                buttonSignUp.setText("Zarejestruj się");
//                loginText.setText(" lub zaloguj się");
//            } else {
//                loginMode = true;
//                buttonSignUp.setText("Zaloguj się");
//                loginText.setText(" lub zarejestruj się");
//            }
//        });
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        buttonSignUp = findViewById(R.id.signUpButton);
        buttonSignUp.setOnClickListener(v -> {
            username = usernameEditText.getText().toString();
            password = passwordEditText.getText().toString();
            int index = 0;
            if (username == "" || password == "") {
                Toast.makeText(LoginActivity.this, "Prosze podać nazwę użytkownika i hasło!", Toast.LENGTH_SHORT).show();
            }
            for (User user : users) {
                if (username.equals(users.get(index).getUsername()) && password.equals(users.get(index).getPassword())) {
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                }
                index++;
            }
            Toast.makeText(LoginActivity.this, "Prosze podać prawidłowo dane użytkownika!", Toast.LENGTH_SHORT).show();

        });
        Button offlineButton=findViewById(R.id.offlineModeButton);
        offlineButton.setOnClickListener(v ->
            offlineMode()
        );

    }
    public void offlineMode(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("W trybie offline nie będziesz mógł skorzystać ze wszystkich funkcji aplikacji.")
                    .setTitle("Czy chcesz kontynuować w trybie offline?")
                    .setPositiveButton("Tak", (dialog, id) -> {
                        offlineMode=true;
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                    })
                    .setNegativeButton("Nie", (dialog, id) -> {
                    });
            builder.create();
            builder.show();
        }
    }

