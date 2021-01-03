package com.mdw31g1.anticovid;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import static com.mdw31g1.anticovid.R.id.buttonEnvoi;

public class BesoinActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btAppel;
    Button btEnvoi;
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
        btEnvoi = findViewById(buttonEnvoi);
        btEnvoi.setOnClickListener(v -> {
            int radioId = radioGroup.getCheckedRadioButtonId();
            radioButton = findViewById(radioId);

            Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:190"));
            sms.putExtra("sms_body", radioButton.getText());
            startActivity(sms);

        });
    }
    public void checkButton() {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Ton choix est: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }


}