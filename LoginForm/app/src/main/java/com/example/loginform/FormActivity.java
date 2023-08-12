package com.example.loginform;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {
    private EditText nameEditText, addressEditText, phoneEditText;
    private Button submitButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        Intent intent;

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.submitButton) {
                String name = nameEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                intent = new Intent(FormActivity.this, InfoActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }
        }
    };


}
