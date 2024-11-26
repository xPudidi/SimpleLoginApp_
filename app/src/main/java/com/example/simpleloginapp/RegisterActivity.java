package com.example.simpleloginapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegisterUsername, etRegisterPassword;
    Button btnRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegisterUsername = findViewById(R.id.etRegisterUsername);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        btnRegister = findViewById(R.id.btnRegister);
        db = new DatabaseHelper(this);

        btnRegister.setOnClickListener(v -> {
            String username = etRegisterUsername.getText().toString().trim();
            String password = etRegisterPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Harap isi semua field!", Toast.LENGTH_SHORT).show();
            } else {
                boolean success = db.registerUser(username, password);
                if (success) {
                    Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                    finish(); // Kembali ke layar login
                } else {
                    Toast.makeText(this, "Username sudah terdaftar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
