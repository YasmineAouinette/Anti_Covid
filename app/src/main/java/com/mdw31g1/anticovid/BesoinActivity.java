package com.mdw31g1.anticovid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BesoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_besoin);

        Button buttonAppel = findViewById(R.id.buttonAppel);
        buttonAppel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri telephone = Uri.parse("tel:190");
                Intent secondeActivity = new Intent(Intent.ACTION_DIAL, telephone);
                PackageManager manager = getPackageManager();
                ComponentName component = secondeActivity.resolveActivity(manager);
                // On vérifie que component n'est pas null
                if (component != null) {
                    startActivity(secondeActivity);
                    finish();
                }
            }
        });


    }
}