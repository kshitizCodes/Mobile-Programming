package com.example.loginform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {
    TextView nameTextView, addressTextView, phoneTextView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String phone = intent.getStringExtra("phone");
        nameTextView = findViewById(R.id.nameTextView);
        addressTextView = findViewById(R.id.addressTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        nameTextView.setText(name);
        addressTextView.setText(address);
        phoneTextView.setText(phone);
    }
}
