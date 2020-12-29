package com.mdw31g1.anticovid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn;
        btn = findViewById(R.id.AlreadyhaveAccount);
        btn.setOnClickListener(v -> startActivity (new Intent(RegisterActivity.this,LoginActivity.class)));
    }
}