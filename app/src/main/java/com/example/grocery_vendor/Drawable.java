package com.example.grocery_vendor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Drawable extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_drawable);

        TextView back =findViewById(R.id.button);
        back.setOnClickListener(view ->{
            mAuth.signOut();
            finishAffinity();
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        } );
    }
}
