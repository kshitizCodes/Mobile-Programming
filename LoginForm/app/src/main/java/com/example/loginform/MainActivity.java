package com.example.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    private EditText emailEditText, passwordEditText;
    private Button loginButton;

    private TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.submitButton);
        loginButton.setOnClickListener(clickListener);

        forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);
        forgotPasswordTextView.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.submitButton) {
                intent = new Intent(MainActivity.this, FormActivity.class);
                startActivity(intent);
            }

            if (v.getId() == R.id.forgotPasswordTextView) {
                Toast.makeText(MainActivity.this, "Remember it next time!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
