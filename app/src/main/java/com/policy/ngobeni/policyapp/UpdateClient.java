package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.policy.ngobeni.policyapp.pojos.Client;

public class UpdateClient extends AppCompatActivity {
    private String _key = "";
    Client client = new Client();
    private FirebaseUser _fbuser;
    private DatabaseReference _databaseReference;
    private StorageReference _storageReference;
    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener _authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_client);

        _key = getIntent().getStringExtra("key");

        //INITIALIZING FIREBASE CONTENT
        _storageReference = FirebaseStorage.getInstance().getReference();
        _databaseReference = FirebaseDatabase.getInstance().getReference().child("Clients").child(_key);
        FirebaseAuth _user = FirebaseAuth.getInstance();
        _fbuser = _user.getCurrentUser();

        _databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                client = (Client) dataSnapshot.getValue();
                //TODO populate the views with data
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(),Home.class));
        finish();
    }
}
