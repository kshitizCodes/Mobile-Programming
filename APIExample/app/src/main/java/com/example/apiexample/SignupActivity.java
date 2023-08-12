// Signup Activity
package com.example.apiexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.*;
import com.android.volley.toolbox.*;


import org.json.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

        Button signupButton = findViewById(R.id.signupButton);
        signupButton.setOnClickListener(clickListener);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            if (v.getId() == R.id.signupButton) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String signUpUrl = "http://127.0.0.1:8000/signup";

                if (!isValidEmail(email)) {
                    Toast.makeText(SignupActivity.this, "Email address not valid!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Password and Confirm Password don't match.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
                StringRequest stringRequest = new StringRequest(
                        Request.Method.POST, signUpUrl,
                        response ->
                        {
                            Toast.makeText(SignupActivity.this, response, Toast.LENGTH_LONG).show();
                        },
                        error -> {
                            Toast.makeText(SignupActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }) {
                    @Override
                    public byte[] getBody() {
                        JSONObject json = new JSONObject();
                        try {
                            json.put("email", email);
                            json.put("password", password);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return json.toString().getBytes();
                    }

                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };
                requestQueue.add(stringRequest);
            }

            if (v.getId() == R.id.backButton) {
                intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };

    static boolean isValidEmail(String email) {
        String regex = "^[A-Z0-9._%+\\-]+@[A-Z0-9.\\-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
