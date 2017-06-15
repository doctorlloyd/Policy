package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class UpdateClient extends AppCompatActivity {
    private String _key = "";
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
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(),Home.class));
        finish();
    }
}
