package com.example.grocery_vendor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_login);

        TextView login= findViewById(R.id.txtLogin);
        EditText input1 = findViewById(R.id.edtemail);
        EditText input2 = findViewById(R.id.edtpass);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input1.getText() != null ) {
                    change_home(input1.getText().toString(), input2.getText().toString());
                }
                else{
                    Toast.makeText(Login.this, "Please input Valid credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView signup = findViewById(R.id.txtSignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changesign();
            }
        });
    }

    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    //Implementing function to navigate to Signup class
    private void changesign() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    //Implementing function to navigate to Home screen
    private void change_home(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
