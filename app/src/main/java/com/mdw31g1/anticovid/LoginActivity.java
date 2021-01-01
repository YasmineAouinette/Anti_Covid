package com.mdw31g1.anticovid;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;


import androidx.appcompat.app.AppCompatActivity;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 105;
    //Déclaration des variables
    TextView btn;
    EditText inputEmail, inputPassword;
    Button btnLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog mloadingBar;
    GoogleSignInClient mGoogleSignInClient;
    CallbackManager mCallbackManager;
    LoginButton loginButton;
    private TextView ForgotMPasse;

    @SuppressLint("WrongViewCast")
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

        ForgotMPasse=findViewById(R.id.ForgotMPasse);
        ForgotMPasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetEmail= new EditText(v.getContext());

            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
       // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Button btnGoogle = findViewById(R.id.btnGoogle);
        btnGoogle.setOnClickListener(v -> signIn());

     //intitialisation de facebook sdk
        FacebookSdk.sdkInitialize(LoginActivity.this);

        // Initialize Facebook Login button
         mCallbackManager = CallbackManager.Factory.create();
        loginButton =findViewById(R.id.btnFacebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                checkIdentifiants();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });

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
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();

            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this,user.getEmail() + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                        updateUI();
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                        updateUI();
                    }

                });
    }

    private void updateUI() {
        Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
        startActivity(intent);
    }

    // REDIRECTION DE L'UTILISATEUR AU TABLEAU DE BORD
    private void SendUserToDashboard() {
        Intent LoginIntent = new Intent(LoginActivity.this, DashboardActivity.class);
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

