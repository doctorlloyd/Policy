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
import com.policy.ngobeni.policyapp.pojos.Member;

import static com.policy.ngobeni.policyapp.R.id.btnAddDependent;
import static com.policy.ngobeni.policyapp.R.id.btnUpdate;
import static com.policy.ngobeni.policyapp.R.id.etRegDependent_relationship;

public class Dependent extends AppCompatActivity implements View.OnClickListener{
    private String _key;
    private Spinner spinner;
    private LinearLayout loginLayout, registerLayout;
    private EditText etRegName, etRegSurname, etRegIDNumber, etRegRelationship;
    //TODO create a view for gender where i will get the value to populate the _gender variable
    private String _name, _surname, _IDNumber, _gender, _relationship;

    private FirebaseUser _fbuser;
    private DatabaseReference _databaseReference;
    private StorageReference _storageReference;
    //[START declare_auth_listener]
    private FirebaseAuth.AuthStateListener _authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dependent_registration);
        initialize();

        try{
            _key = getIntent().getStringExtra("_key");
        }catch(Exception e)
        {
            System.out.print("=====================================================: "+_key);
        }
    }

    void initialize(){
        Button btnDone = (Button) findViewById(R.id.btnDoneDependent);
        btnDone.setOnClickListener(this);
        Button btnAddDependent = (Button) findViewById(R.id.btnRegisterDependent);
        btnAddDependent.setOnClickListener(this);

        //EDIT TEXT TO BE INITIALIZED
        etRegName = (EditText) findViewById(R.id.etRegDependent_name);
        etRegSurname = (EditText) findViewById(R.id.etRegDependent_surname);
        etRegIDNumber = (EditText) findViewById(R.id.etRegDependent_id_number);
        etRegRelationship = (EditText) findViewById(R.id.etRegDependent_relationship);

        spinner = (Spinner) findViewById(R.id.dependent_gender_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //INITIALIZING FIREBASE CONTENT
        _storageReference = FirebaseStorage.getInstance().getReference();
        _databaseReference = FirebaseDatabase.getInstance().getReference().child("Clients").child(_key).child("Dependents");
        FirebaseAuth _user = FirebaseAuth.getInstance();
        _fbuser = _user.getCurrentUser();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(),UpdateClient.class);
        intent.putExtra("_key",_key);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnRegisterDependent:
                if(validateInputsRegistration())
                {
                    //String firstName, String lastName, Long idNumber, String relationShip
                    Member member = new Member(_name,_surname,Long.parseLong(_IDNumber),_relationship,String.valueOf(spinner.getSelectedItem()));
                    _databaseReference.push().setValue(member);
                }else {
                    Toast.makeText(getBaseContext(),"Missing inputs...",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnDoneDependent:
                onBackPressed();
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

        _relationship = etRegRelationship.getText().toString();
        if (TextUtils.isEmpty(_relationship)) {
            etRegRelationship.setError("Required.");
            return false;
        } else {
            etRegRelationship.setError(null);
        }
        return true;
    }
}
