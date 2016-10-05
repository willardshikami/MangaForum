package com.example.willardpc.mangaforum;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    //DEFINE VIEWS
    private Button buttonRegister;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //INITIALIZE VIEWS

        firebaseAuth = FirebaseAuth.getInstance();

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);

        textViewSignin.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    private void registerUser(){
        //String username = editTextUsername.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //if (TextUtils.isEmpty(username)){}
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

        progressDialog.setMessage("Registering please wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //USER RESGISTERED SUCCESSFULLY
                            //PROFILE ACTIVITY STARTS HERE
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Failed to register, please try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == buttonRegister){
            registerUser();
        }

        if(view == textViewSignin){
            //OPENS LOGIN ACTIVITY
        }
    }
}
