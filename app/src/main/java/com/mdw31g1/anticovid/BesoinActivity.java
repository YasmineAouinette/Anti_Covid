package com.mdw31g1.anticovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BesoinActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btAppel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besoin);

        btAppel = findViewById(R.id.buttonAppel);
        btAppel.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:190"));
            startActivity(intent);
        });
        radioGroup = findViewById(R.id.radiogroup);
    }

    public void onClickListenerButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this,"Ton aide est"+radioButton.getText(), Toast.LENGTH_SHORT).show();

    }


    public void onClick(View view) {
    }}