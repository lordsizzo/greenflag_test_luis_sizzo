package com.example.greenflag_test_luis_sizzo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private Button btnCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        btnCreateAccount = findViewById(R.id.btnCreateAnAccount);

        btnCreateAccount.setOnClickListener(v -> {

            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

    }
}