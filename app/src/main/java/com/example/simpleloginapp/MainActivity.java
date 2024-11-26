package com.example.simpleloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();


            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua field!", Toast.LENGTH_SHORT).show();
            } else {
                boolean userExists = db.checkUser(username, password);
                if (userExists) {
                    Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();
                    // Lanjutkan ke aktivitas berikutnya
                } else {
                    Toast.makeText(this, "Username atau password salah.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
