package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
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

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener, View.OnClickListener {

    private SearchView searchAutoComplete;
    private FirebaseUser _fbuser;
    private DatabaseReference _databaseReference;
    private String _id;
    private Button btnUpdateHome;
    private StorageReference _storageReference;
    private FirebaseAuth.AuthStateListener _authListener;
    private Client client;

    private EditText etEditName, etEditSurname, etEditIDNumber, etEditAddress, etEditGender, etEditContact, etEditAmount;
    private String _key = "_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialize();

        searchAutoComplete = (SearchView) findViewById(R.id.search_client_searchView);
        searchAutoComplete.setSubmitButtonEnabled(true);
        searchAutoComplete.setOnQueryTextListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.search_client_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchAutoComplete.setVisibility(View.VISIBLE);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            createExitDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.lightTheme) {
            return true;
        } else if (id == R.id.darkTheme) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            startActivity(new Intent(getBaseContext(), Register.class));
            finish();
        } else if (id == R.id.nav_list_clients) {
            startActivity(new Intent(getBaseContext(), ListClients.class));
            finish();
        } else if (id == R.id.nav_location) {
            startActivity(new Intent(getBaseContext(), Location.class));
            finish();
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(getBaseContext(), Settings.class));
            finish();

        } else if (id == R.id.nav_share) {
            //TODO an intent to trigger social media Api

        } else if (id == R.id.nav_send) {
            //TODO an intent to trigger sms Api

        } else if (id == R.id.nav_about_us) {
            //TODO a TextView to display information about the company
            Intent intent = new Intent("android.intent.action.ABOUT_US");
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createExitDialog() {
        //TODO a dialog box to exit the app
        super.onBackPressed();
    }

    @Override
    public boolean onQueryTextSubmit(final String id_number) {
        searchAutoComplete.setVisibility(View.GONE);
        _id = id_number;
        _databaseReference.addValueEventListener(evantListener);
        return false;
    }

    ValueEventListener evantListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                client = ds.getValue(Client.class);
                //Todo
                if (client.getIdNumber().equals(Long.parseLong(_id))) {
                    etEditName.setText(client.getFirstName());
                    etEditSurname.setText(client.getLastName());
                    etEditIDNumber.setText(String.valueOf(client.getIdNumber()));
                    etEditAddress.setText(client.getAddress());
                    etEditContact.setText(client.getCellNumber());
                    etEditGender.setText(client.getGender());
                    _key = ds.getKey();
                    _databaseReference.removeEventListener(this);
                    btnUpdateHome.setEnabled(true);
                }
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    void initialize() {
        btnUpdateHome = (Button) findViewById(R.id.edit_profile);
        btnUpdateHome.setOnClickListener(this);

        //EDIT TEXT TO BE INITIALIZED
        etEditName = (EditText) findViewById(R.id.etHomeFirstname);
        etEditSurname = (EditText) findViewById(R.id.etHomeLastname);
        etEditIDNumber = (EditText) findViewById(R.id.etHomeIDnumber);
        etEditAddress = (EditText) findViewById(R.id.etHomeAddress);
        etEditGender = (EditText) findViewById(R.id.etHomeGender);
        etEditContact = (EditText) findViewById(R.id.etHomeContact);
        etEditAmount = (EditText) findViewById(R.id.etHomeAmount);

        //INITIALIZING FIREBASE CONTENT
        _databaseReference = FirebaseDatabase.getInstance().getReference().child("Clients");
    }

    @Override
    public void onClick(View view) {
        int view_id = view.getId();
        switch (view_id) {
            case R.id.edit_profile:
                Intent intent = new Intent(getBaseContext(), UpdateClient.class);
                intent.putExtra("_key", _key);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}
