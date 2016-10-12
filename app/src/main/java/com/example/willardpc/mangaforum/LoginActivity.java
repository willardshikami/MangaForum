package com.example.willardpc.mangaforum;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements View.OnClickListener{

    private Button buttonLogin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    private TextView t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();{
            if (firebaseAuth.getCurrentUser() != null){
                //START PROFILE ACTIVITY
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }

        progressDialog = new ProgressDialog(this);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup = (TextView) findViewById(R.id.textViewSignup);

        buttonLogin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

        t = (TextView) findViewById(R.id.textViewLogin);
        Typeface myCustomFonts=Typeface.createFromAsset(getAssets(), "fonts/VarelaRound-Regular.ttf");
        t.setTypeface(myCustomFonts);

    }


    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //EMAIL IS EMPTY
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            //STOPS FUNCTION FROM EXECUTING FURRTHER
            return;
        }

        if (TextUtils.isEmpty(password)){
            //PASSWORD IS EMPTY
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            //STOPS THE FUNCTION FROM EXECUTING FURTHER
            return;
        }

        //IF EMAIL AND VALIDATION SUCCESSFULL
        //SHOW PROGRESS DIALOG

        progressDialog.setMessage("Logging in please wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogin){
            userLogin();
        }
        if (view == textViewSignup){
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}
