package pl.milosz.medbase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pl.milosz.medbase.DB.GetAccountsInfo;

public class LoginActivity extends AppCompatActivity {
    Boolean loginMode = true;
    TextView loginText;
    EditText usernameEditText;
    EditText passwordEditText;
    Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            connected = true;
            new GetAccountsInfo(getApplicationContext()).execute();
        }
        Log.i("guwno połączenie: ",String.valueOf(connected));
        setContentView(R.layout.activity_login);
        loginText = findViewById(R.id.loginText);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginMode) {
                    loginMode = false;
                    buttonSignUp.setText("Zarejestruj się");
                    loginText.setText(" lub zaloguj się");
                } else {
                    loginMode = true;
                    buttonSignUp.setText("Zaloguj się");
                    loginText.setText(" lub zarejestruj się");
                }
            }
        });
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        buttonSignUp = findViewById(R.id.signUpButton);
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (username == "" || password == "") {
                    Toast.makeText(LoginActivity.this, "Prosze podać nazwę użytkownika i hasło!", Toast.LENGTH_SHORT).show();
                } else if (username.equals("admin") && password.equals("admin")) {
                    Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(LoginActivity.this, "Prosze podać prawidłowo dane użytkownika!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
