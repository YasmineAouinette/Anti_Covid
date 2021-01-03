package com.mdw31g1.anticovid;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class LogoutActivity extends AppCompatActivity  {
    LogoutButton logoutButton;

    // REDIRECTION DE L'UTILISATEUR AU LOGIN
    private void SendUserToLogin() {
        Intent LoginIntent = new Intent(LogoutActivity.this, LoginActivity.class);
        LogoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(LogoutIntent);
    }
}
