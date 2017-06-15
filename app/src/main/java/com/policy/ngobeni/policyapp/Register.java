package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button btnSignIn,btnCancel,btnRegister;
    private LinearLayout loginLayout, registerLayout;
    private EditText etRegName,etRegSurname,etRegIDNumber,etRegAddress1,etRegAddress2,etRegAddress3,etRegCode,etRegContact;
    private FirebaseAuth user;
    private FirebaseUser fbuser;
    private DatabaseReference databaseReference;
    private Uri imageUri;
    private StorageReference mStorageReference;

    // [START declare_auth_listener]
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initialise();

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(),Home.class));
        finish();
    }
    void initialise()
    {
        //INITIALIZING BUTTONS
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        //INITIALIZING LAYOUTS
        registerLayout = (LinearLayout) findViewById(R.id.register_layout);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);

        //EDIT TEXT TO BE INITIALIZED
        etRegName = (EditText) findViewById(R.id.etReg_name);
        etRegSurname = (EditText) findViewById(R.id.etReg_surname);
        etRegIDNumber = (EditText) findViewById(R.id.etReg_id_number);
        etRegAddress1 = (EditText) findViewById(R.id.etReg_address1);
        etRegAddress2 = (EditText) findViewById(R.id.etReg_address2);
        etRegAddress3 = (EditText) findViewById(R.id.etReg_address3);
        etRegCode = (EditText) findViewById(R.id.etReg_address_code);
        etRegContact = (EditText) findViewById(R.id.etReg_client_contact);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnCancel:
                registerLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                setTitle("Admin Login");
                break;
            case R.id.btnRegister:
                if(validateInputsRegistration())
                {

                }
                break;
            case R.id.btnSignIn:
                registerLayout.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
                setTitle("Client Registration");
                break;
            default:
                break;
        }
    }
    boolean validateInputsRegistration()
    {
        String _name = etRegName.getText().toString();
        if (TextUtils.isEmpty(_name)) {
            etRegName.setError("Required.");
            return false;
        } else {
            etRegName.setError(null);
        }

        String _surname = etRegSurname.getText().toString();
        if (TextUtils.isEmpty(_surname)) {
            etRegSurname.setError("Required.");
            return false;
        } else {
            etRegSurname.setError(null);
        }

        String _IDNumber = etRegIDNumber.getText().toString();
        if (TextUtils.isEmpty(_IDNumber)) {
            etRegIDNumber.setError("Required.");
            return false;
        } else {
            etRegIDNumber.setError(null);
        }

        String address1 = etRegAddress1.getText().toString();
        if (TextUtils.isEmpty(address1)) {
            etRegAddress1.setError("Required.");
            return false;
        } else {
            etRegAddress1.setError(null);
        }

        String _code = etRegCode.getText().toString();
        if (TextUtils.isEmpty(_code)) {
            etRegCode.setError("Required.");
            return false;
        } else {
            etRegCode.setError(null);
        }

        String _contact = etRegContact.getText().toString();
        if (TextUtils.isEmpty(_contact)) {
            etRegContact.setError("Required.");
            return false;
        } else {
            etRegContact.setError(null);
        }
        return false;
    }
}
