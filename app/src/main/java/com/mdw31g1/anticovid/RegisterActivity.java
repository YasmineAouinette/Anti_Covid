package com.mdw31g1.anticovid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    //declaration des variables
    TextView btn;
    Button btnregister;
    private FirebaseAuth mAuth;

    private EditText inputUsername,inputEmail,inputPassword,inputConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

   // Récuperation des données de view
            btn = findViewById(R.id.AlreadyhaveAccount);
            inputUsername = findViewById(R.id.inputPasssword);
            inputEmail= findViewById(R.id.inputEmail);
            inputPassword= findViewById(R.id.inputpassword);
            inputConfirmPassword= findViewById(R.id.inputConfirmPassword);
            btnregister=findViewById(R.id.btnregister);
            btnregister.setOnClickListener(v -> checkIdentifiants());

        btn.setOnClickListener(v -> startActivity (new Intent(RegisterActivity.this,LoginActivity.class)));
    }
    // verification des identifiants
    private void checkIdentifiants(){
        String username=inputUsername.getText().toString();
        String email =inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmpassword=inputConfirmPassword.getText().toString();
       if (username.isEmpty() || username.length()<8){
           showError(inputUsername,"votre nom n'est pas valide!");
       }else {
           if (email.isEmpty() || !email.contains("@")){
               showError(inputEmail,"Email n'est pas valide!");
           } else {
               if (password.isEmpty() || password.length()<8){
                   showError(inputPassword,"mot de passe n'est pas valide");
               }else if (confirmpassword.isEmpty() || !confirmpassword.equals(password)){
                   showError(inputConfirmPassword,"le mot de passe ne correspond pas!");
           }
               else{
                   }

           }
       }
    }

 // affichage d'un message d'erreur au cas de saisir des données incorrect
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}