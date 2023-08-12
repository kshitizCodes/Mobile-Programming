package com.example.apiexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button loginButton;
    private Button signupButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        signupButton = findViewById(R.id.signupButton);

        loginButton.setOnClickListener(clickListener);
        signupButton.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.loginButton) {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            if (v.getId() == R.id.signupButton) {
                intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        }
    };
}
