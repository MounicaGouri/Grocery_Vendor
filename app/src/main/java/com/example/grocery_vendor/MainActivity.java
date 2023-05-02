package com.example.grocery_vendor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public DocumentSnapshot data;

    public TextView loc;
    private final LocationListener locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Convert location into address
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses;
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                String address = addresses.get(0).getAddressLine(0);
                loc = findViewById(R.id.textView5);
                loc.setText(address);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };




        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_main);

        ImageView home = findViewById(R.id.imgHome);
        home.setOnClickListener(a->{
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        ImageView profile = findViewById(R.id.imgUser);
        profile.setOnClickListener(b->{
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        });
            ImageView logout = findViewById(R.id.imgLogout);
            logout.setOnClickListener(c->{
                mAuth.signOut();
                finishAffinity();
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            });

        ImageView draw = findViewById(R.id.imageView4);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changedrawable();
            }
        });

        TextView txt1 = findViewById(R.id.textView7);
        txt1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt1.getText().toString();
                txt1.setTextColor(R.color.grey);
                changeveg();
            }
        });

        TextView txt2 = findViewById(R.id.textView8);
        txt2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt2.getText().toString();
                txt2.setTextColor(R.color.grey);
                changebakery();
            }
        });

        TextView txt3 = findViewById(R.id.textView9);
        txt3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String x = txt3.getText().toString();
                txt3.setTextColor(R.color.grey);
                changeFrozen();
            }
        });
//            txt3.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    changeFrozen();
//                }
//            });
    }


    private void changeFrozen() {
        Intent i5 =new Intent(this,Frozen.class);
        startActivity(i5);
    }

    private void changebakery() {
        Intent i4 =new Intent(this,Bakery.class);
        startActivity(i4);
    }

    private void changeveg() {
            Intent i3 =new Intent(this,Vegetables.class);
            startActivity(i3);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loc = findViewById(R.id.textView5);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please enable the location permision!", Toast.LENGTH_SHORT).show();
            return;
        }
// Request location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }
        private void changedrawable() {
        Intent i2 =new Intent(this,Drawable.class);
        startActivity(i2);
    }

    private void changeprofile() {
        Intent i =new Intent(this,Profile.class);
        startActivity(i);
    }
}