package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.policy.ngobeni.policyapp.pojos.Client;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner;
    private LinearLayout loginLayout, registerLayout;
    private EditText etRegName, etRegSurname, etRegIDNumber, etRegAddress1, etRegAddress2, etRegAddress3, etRegCode, etRegContact;
    //TODO create a view for gender where i will get the value to populate the _gender variable
    private String _name, _surname, _IDNumber, _contact, _code, _address1;

    private FirebaseUser _fbuser;
    private DatabaseReference _databaseReference;
    private StorageReference _storageReference;
    //[START declare_auth_listener]
    private FirebaseAuth.AuthStateListener _authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initialise();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), Home.class));
        finish();
    }

    void initialise() {
        //INITIALIZING BUTTONS
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

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

        spinner = (Spinner) findViewById(R.id.gender_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //INITIALIZING FIREBASE CONTENT
        _storageReference = FirebaseStorage.getInstance().getReference();
        _databaseReference = FirebaseDatabase.getInstance().getReference().child("Clients");
        FirebaseAuth _user = FirebaseAuth.getInstance();
        _fbuser = _user.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancel:
                registerLayout.setVisibility(View.GONE);
                loginLayout.setVisibility(View.VISIBLE);
                setTitle("Admin Login");
                break;
            case R.id.btnRegister:
                if (validateInputsRegistration()) {
                    Client _client;
                    String _address2 = etRegAddress2.getText().toString();
                    String _address3 = etRegAddress3.getText().toString();
                    String _gender = (String) spinner.getSelectedItem();
                    if (!_address2.isEmpty() && !_address3.isEmpty()) {
                        _client = new Client(_name, _surname, Long.parseLong(_IDNumber), _address1 + ", "
                                + _address2 + ", " + _address3 + ", " + _code, _contact, _gender);
                    } else {
                        _client = new Client(_name, _surname, Long.parseLong(_IDNumber), _address1 + ", " + _code, _contact, _gender);
                    }
                    String _key = _databaseReference.push().getKey();
                    _databaseReference.child(_key).setValue(_client);
                    Toast.makeText(getBaseContext(), "Client successfully registered!..", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), UpdateClient.class);
                    intent.putExtra("key", _key);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }

    boolean validateInputsRegistration() {
        _name = etRegName.getText().toString();
        if (TextUtils.isEmpty(_name)) {
            etRegName.setError("Required.");
            return false;
        } else {
            etRegName.setError(null);
        }

        _surname = etRegSurname.getText().toString();
        if (TextUtils.isEmpty(_surname)) {
            etRegSurname.setError("Required.");
            return false;
        } else {
            etRegSurname.setError(null);
        }

        _IDNumber = etRegIDNumber.getText().toString();
        if (TextUtils.isEmpty(_IDNumber)) {
            etRegIDNumber.setError("Required.");
            return false;
        } else {
            etRegIDNumber.setError(null);
        }

        _address1 = etRegAddress1.getText().toString();
        if (TextUtils.isEmpty(_address1)) {
            etRegAddress1.setError("Required.");
            return false;
        } else {
            etRegAddress1.setError(null);
        }

        _code = etRegCode.getText().toString();
        if (TextUtils.isEmpty(_code)) {
            etRegCode.setError("Required.");
            return false;
        } else {
            etRegCode.setError(null);
        }

        _contact = etRegContact.getText().toString();
        if (TextUtils.isEmpty(_contact)) {
            etRegContact.setError("Required.");
            return false;
        } else {
            etRegContact.setError(null);
        }
        return true;
    }
}
