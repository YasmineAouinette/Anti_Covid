package com.mdw31g1.anticovid;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(v -> {
            //startActivity(onNewIntent(LoginActivity.this.RegisterActivity.class));
        });
    }
}