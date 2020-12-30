package com.mdw31g1.anticovid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    //Déclaration des variables
    TextView btn;
    EditText inputEmail, inputPassword;
    Button btnLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog mloadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // initialisation des données
        btn = findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputpassword);
        mAuth = FirebaseAuth.getInstance();
        mloadingBar = new ProgressDialog(LoginActivity.this);


        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(v -> checkIdentifiants());
    }

    // verification des identifiants
    private void checkIdentifiants() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Email n'est pas valide!");
        } else {
            if (password.isEmpty() || password.length() < 8) {
                showError(inputPassword, "mot de passe n'est pas valide!");
            } else {
                // progress bar
                mloadingBar.setTitle("Connexion");
                mloadingBar.setMessage("Veuillez patienter pendant que vos vérifiez vos identifiants");
                mloadingBar.setCanceledOnTouchOutside(false);
                mloadingBar.show();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Connexion réussite!", Toast.LENGTH_SHORT).show();
                        mloadingBar.dismiss();
                        SendUserToDashboard();
                    } else {
                        Toast.makeText(LoginActivity.this, "Échec de connexion, veuillez réessayer.....", Toast.LENGTH_SHORT).show();
                    }
                });


            }

        }
    }

    // REDIRECTION DE L'UTILISATEUR AU TABLEAU DE BORD
    private void SendUserToDashboard() {
        Intent LoginIntent = new Intent(LoginActivity.this, LoginActivity.class);
        LoginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(LoginIntent);
    }

    // affichage d'un message d'erreur au cas de saisir des données incorrect
    private void showError(EditText input, String s) {
        input.setError(s);
        input.setError(s);
        input.requestFocus();
    }
}

