package com.example.grocery_vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_signup);
        mAuth =FirebaseAuth.getInstance();

        TextView txt = findViewById(R.id.login);
        txt.setOnClickListener(view ->{
                    change_login(
                    );
                });

        EditText name = findViewById(R.id.edtname);
        EditText input1 = findViewById(R.id.edtemail);
        EditText input2 = findViewById(R.id.edtpass);
        EditText contact = findViewById(R.id.edtcontact);
        TextView signup = findViewById(R.id.txtSignup);

        signup.setOnClickListener(task-> {
            String Name = name.getText().toString();
            String Email = input1.getText().toString();
            String Mobile_Number = contact.getText().toString();
           String Pass = input2.getText().toString();

           change_home(Name, Email, Mobile_Number, Pass);
        });

    }

    private void change_login() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void change_home(String Name, String Email, String Mobile_Number, String Pass) {
        Map<String, Object> data = new HashMap<>();
        data.put("Name", Name);
        data.put("Email", Email);
        data.put("Mobile Number", Mobile_Number);
        db.collection("Grocery_Users").document(Email)
                        .set(data).addOnCompleteListener(
                                task->{
                                    mAuth.createUserWithEmailAndPassword(Email,Pass)
                                            .addOnCompleteListener(this, task1 -> {
                                                    Intent intent = new Intent(this, MainActivity.class);
                                                    startActivity(intent);
                                                    Toast.makeText(getApplicationContext(), "Successfully created.",Toast.LENGTH_SHORT).show();
                                            }).addOnFailureListener(x -> {
                                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            });
                                }
                );

    }
}
