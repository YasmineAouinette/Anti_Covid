package com.mdw31g1.anticovid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    //Déclaration des variables
    TextView btn;
    EditText inputEmail,inputPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn=findViewById(R.id.textViewSignUp);
        inputEmail= findViewById(R.id.inputEmail);
        inputPassword= findViewById(R.id.inputpassword);
        btnLogin=findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(v -> checkIdentifiants());
        btn.setOnClickListener(v -> startActivity (new Intent(LoginActivity.this,RegisterActivity.class)));
    }
    private void checkIdentifiants(){
        String email =inputEmail.getText().toString();
        String password=inputPassword.getText().toString();

            if (email.isEmpty() || !email.contains("@")){
                showError(inputEmail,"Email n'est pas valide!");
            } else {
                if (password.isEmpty() || password.length()<8){
                    showError(inputPassword,"mot de passe n'est pas valide!");
                }
                else{
                    Toast.makeText(this,"appel de la méthode de connexion!",Toast.LENGTH_SHORT).show();
                }

            }
        }


    // affichage d'un message d'erreur au cas de saisir des données incorrect
    private void showError(EditText input, String s) {
        input.setError(s);
        input.setError(s);
        input.requestFocus();
    }
}
