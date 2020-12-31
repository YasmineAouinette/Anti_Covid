package com.mdw31g1.anticovid;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.mdw31g1.anticovid.R.id.buttonEnvoi;

public class BesoinActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btAppel;
    EditText btEnvoi;
    @SuppressLint("WrongViewCast")
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
        btEnvoi = findViewById(R.id.buttonEnvoi);
        btEnvoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:190"));
                    sms.putExtra("sms_body", radioButton.getText());
                    startActivity(sms);

                }
            });
    }

    private void onClickListenerButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this,"Ton aide est"+radioButton.getText(), Toast.LENGTH_SHORT).show();

    }
    public void onRadioButtonClicked(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        EditText editT;
        editT = findViewById(R.id.typeAide);
        RadioButton checked;
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.aide1:
                if (checked) {
                    Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:190"));

                    sms.putExtra("sms_body", radioButton.getText());
                startActivity(sms);
                }

                break;
            case R.id.aide2:
                if (checked){
                    Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:190"));
                sms.putExtra("sms_body", radioButton.getText());
                startActivity(sms);
                }

                break;
            case R.id.aide3:
                if (checked){
                    Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:190"));
                sms.putExtra("sms_body", radioButton.getText());
                startActivity(sms);
                }

                break;
            case R.id.aide4:
                if (checked){
                    Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:190"));
                sms.putExtra("sms_body", editT.getText());
                startActivity(sms);
                }

                break;

        }

    }
}