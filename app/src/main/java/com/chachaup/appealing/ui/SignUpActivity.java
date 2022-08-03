package com.chachaup.appealing.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chachaup.appealing.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.textViewSignIn)
    TextView mSignIn;
    @BindView(R.id.buttonSignup)
    Button mCreateAccount;
    @BindView(R.id.textInputLayoutName)
    TextInputLayout mName;
    @BindView(R.id.textInputLayoutCreateEmail) TextInputLayout mEmail;
    @BindView(R.id.textInputLayoutCreatePassword) TextInputLayout mPassword;
    @BindView(R.id.textInputLayoutConfirmPassword) TextInputLayout mConfirmPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mSignIn.setOnClickListener(this);
        mCreateAccount.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }

    @Override
    public void onClick(View v) {
        if (v == mSignIn){
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
//            finish();
        }
        if (v == mCreateAccount){
            createNewUser();
        }
    }

    private void createNewUser(){
        String name = mName.getEditText().getText().toString().trim();
        String email = mEmail.getEditText().getText().toString().trim();
        String password = mPassword.getEditText().getText().toString().trim();
        String confirmPassword = mConfirmPassword.getEditText().getText().toString().trim();

        boolean validName = isValidName(name);
        boolean validEmail = isValidEmail(email);
        boolean validPassword = isValidPassword(password,confirmPassword);

        if (!validEmail || !validName || !validPassword) return;

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Account has been created successfully",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Account creation failed",Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    // add code to go to main activity here
                    // code block below stands in for the code to main activity
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }

    private boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail){
            mEmail.getEditText().setError("Please enter a valid email address");
            return false;
        }
        return true;
    }

    private boolean isValidName(String name){
        if (name.equals("")){
            mName.getEditText().setError("Enter a valid name");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword){
        if (password.length() < 6){
            mPassword.getEditText().setError("Password should be more than six characters");
            return false;
        }
        if (!password.equals(confirmPassword)){
            mPassword.getEditText().setError("Passwords do not match");
            mConfirmPassword.getEditText().setError("Passwords do not match");
            return false;
        }
        return true;
    }
}