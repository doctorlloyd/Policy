package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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

public class UpdateClient extends AppCompatActivity implements View.OnClickListener {
    Client client = new Client();
    private String _key;
    private EditText etEditName, etEditSurname, etEditIDNumber, etEditAddress, etEditGender, etEditContact;
    private String _name, _surname, _IDNumber, _contact, _address, _gender;

    private Button btnDone, btnUpdade, btnAddDependent;
    private DatabaseReference _databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_client);
        initialise();


        _key = getIntent().getStringExtra("_key");

        //INITIALIZING FIREBASE CONTENT
        _databaseReference = FirebaseDatabase.getInstance().getReference().child("Clients").child(_key);

        FirebaseAuth _user = FirebaseAuth.getInstance();

        _databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                client = dataSnapshot.getValue(Client.class);

                //TODO extracting the object
                _name = client.getFirstName();
                _surname = client.getLastName();
                _IDNumber = String.valueOf(client.getIdNumber());
                _contact = client.getCellNumber();
                _address = client.getAddress();
                _gender = client.getGender();

                //TODO populate the views with data
                etEditAddress.setText(_address);
                etEditGender.setText(_gender);
                etEditName.setText(_name);
                etEditSurname.setText(_surname);
                etEditIDNumber.setText(_IDNumber);
                etEditContact.setText(_contact);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), "error, while fetching a client", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), Home.class));
        finish();
    }

    void initialise() {
        //INITIALIZING BUTTONS
        btnUpdade = (Button) findViewById(R.id.btnUpdate);
        btnUpdade.setOnClickListener(this);
        btnAddDependent = (Button) findViewById(R.id.btnAddDependent);
        btnAddDependent.setOnClickListener(this);
        btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(this);

        //EDIT TEXT TO BE INITIALIZED
        etEditName = (EditText) findViewById(R.id.etEdit_name);
        etEditSurname = (EditText) findViewById(R.id.etEdit_surname);
        etEditIDNumber = (EditText) findViewById(R.id.etEdit_id_number);
        etEditAddress = (EditText) findViewById(R.id.etEdit_address);
        etEditGender = (EditText) findViewById(R.id.etEdit_gender);
        etEditContact = (EditText) findViewById(R.id.etEdit_client_contact);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnUpdate:
                /*
                **********************************
                 */
                _name = etEditName.getText().toString();
                _surname = etEditSurname.getText().toString();
                _IDNumber = etEditIDNumber.getText().toString();
                _address = etEditAddress.getText().toString();
                _contact = etEditContact.getText().toString();
                _gender = etEditGender.getText().toString();
                /*
                *****************************
                 */
                client = new Client(_name, _surname, Long.parseLong(_IDNumber), _address, _contact, _gender);
                _databaseReference.setValue(client);
                /*
                *********************************
                 */
                etEditName.setEnabled(false);
                etEditSurname.setEnabled(false);
                etEditIDNumber.setEnabled(false);
                etEditAddress.setEnabled(false);
                etEditContact.setEnabled(false);
                etEditGender.setEnabled(false);
                /*
                ******************************************
                 */
                btnUpdade.setVisibility(View.GONE);
                btnAddDependent.setVisibility(View.VISIBLE);
                btnDone.setVisibility(View.VISIBLE);
                Toast.makeText(getBaseContext(), "Updated", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnAddDependent:
                Intent intent = new Intent(getBaseContext(), Dependent.class);
//                intent.putExtra("_client", client);
                intent.putExtra("_key", _key);
                startActivity(intent);
                finish();
                break;
            case R.id.btnDone:
                startActivity(new Intent(getBaseContext(), Register.class));
                finish();
                break;
            default:
                break;
        }
    }
}
