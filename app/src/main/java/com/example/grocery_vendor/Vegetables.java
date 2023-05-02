package com.example.grocery_vendor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Vegetables extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_vegetables);


        TextView ghome = findViewById(R.id.textView30);
        ghome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changehome();
            }
        });
        TextView gcart = findViewById(R.id.textView31);
        gcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changecart();
            }
        });
        TextView txt1 = findViewById(R.id.textView28);
        txt1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt1.getText().toString();
                txt1.setTextColor(R.color.grey);
            }
        });
        TextView txt2 = findViewById(R.id.textView27);
        txt2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt2.getText().toString();
                txt2.setTextColor(R.color.grey);
            }
        });
        TextView txt3 = findViewById(R.id.textView26);
        txt3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt3.getText().toString();
                txt3.setTextColor(R.color.grey);
            }
        });
        TextView txt4 = findViewById(R.id.textView25);
        txt4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt4.getText().toString();
                txt4.setTextColor(R.color.grey);
            }
        });
    }

    private void changecart() {
        Intent i1 = new Intent(this,Cart.class);
        startActivity(i1);
    }

    private void changehome() {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}