package com.example.grocery_vendor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cart extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_cart);

        TextView paylater = findViewById(R.id.textView42);
        paylater.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = paylater.getText().toString();
                paylater.setTextColor(R.color.purple_200);
            }
        });
        TextView btn = findViewById(R.id.textView41);
        btn.setOnClickListener(
                task->
                {   String Email = mAuth.getCurrentUser().getEmail().toString();
                    Map < String, Object > data = new HashMap<>();
        data.put("Items", "[Tomato, Donuts, Meat ");
        data.put("Weight", "[1KG, 1KG, 1KG]");
        data.put("Delivery Place", "London");
        data.put("Price", " $300");
        data.put("Discount", "None");

        db.collection("Grocery_Orders").document(Email)
                .set(data).addOnCompleteListener(
                        task1 -> {
                            changepayment();
                        }
                );
        }
            );

    }

    private void changepayment() {
        Intent i = new Intent(this, Payment.class);
        startActivity(i);

    }
}
