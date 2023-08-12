// Login Activity
package com.example.apiexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    Intent intent;

    private EditText emailEditText, passwordEditText;
    private Button loginButton, backButton;
    private TextView forgotPasswordTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(clickListener);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(clickListener);

        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        forgotPasswordTextView.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.forgotPasswordTextView) {
                Toast.makeText(LoginActivity.this, "Remember it next time!", Toast.LENGTH_SHORT).show();
            }

            if (v.getId() == R.id.backButton) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            if (v.getId() == R.id.loginButton) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Toast.makeText(LoginActivity.this, "Login Button clicked!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
