package com.example.manara_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText username = findViewById(R.id.editTextTextPersonName);
       EditText password = findViewById(R.id.editTextTextPassword);
       Button btn = findViewById(R.id.button3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String us = username.getText().toString();
                final String ps = password.getText().toString();


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

                Query check = reference.orderByChild("username").equalTo(us);

                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override


                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            username.setError(null);


                            String passwordEntered = dataSnapshot.child(us).child("password").getValue(String.class);

                            if (passwordEntered.equals(ps)) {
                                username.setError(null);


                                String UsernameEntered = dataSnapshot.child(us).child("username").getValue(String.class);
                                String phoneEntered = dataSnapshot.child(us).child("phone").getValue(String.class);

                                Intent intent = new Intent(getApplicationContext(), aks.class);

                                intent.putExtra("username", UsernameEntered);
                                intent.putExtra("password", passwordEntered);


                                startActivity(intent);


                            } else {
                                password.setError("Wrong password");
                                password.requestFocus();
                            }

                        } else {
                            username.setError("NO such user exist");
                            username.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }
}