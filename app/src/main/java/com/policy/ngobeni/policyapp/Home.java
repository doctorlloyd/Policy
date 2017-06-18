package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        SearchView.OnQueryTextListener, View.OnClickListener {

    private SearchView searchAutoComplete;
    private FirebaseUser _fbuser;
    private DatabaseReference _databaseReference;
    private StorageReference _storageReference;
    //[START declare_auth_listener]
    private FirebaseAuth.AuthStateListener _authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchAutoComplete = (SearchView)findViewById(R.id.search_client_searchView);
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
        }else if(id == R.id.darkTheme) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_register) {
            startActivity(new Intent(getBaseContext(),Register.class));
            finish();
        } else if (id == R.id.nav_list_clients) {
            startActivity(new Intent(getBaseContext(),ListClients.class));
            finish();
        } else if (id == R.id.nav_location) {
            startActivity(new Intent(getBaseContext(),Location.class));
            finish();
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(getBaseContext(),Settings.class));
            finish();

        } else if (id == R.id.nav_share) {
            //TODO an intent to trigger social media Api

        } else if (id == R.id.nav_send) {
            //TODO an intent to trigger sms Api

        } else if(id==R.id.nav_about_us)
        {
            //TODO a TextView to display information about the company
            Intent intent = new Intent("android.intent.action.ABOUT_US");
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void createExitDialog()
    {
        //TODO a dialog box to exit the app
        super.onBackPressed();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
        searchAutoComplete.setVisibility(View.GONE);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
    void initialize(){
        Button btnDone = (Button) findViewById(R.id.btn);
        btnDone.setOnClickListener(this);

        //INITIALIZING FIREBASE CONTENT
        _storageReference = FirebaseStorage.getInstance().getReference();
        _databaseReference = FirebaseDatabase.getInstance().getReference().child("Clients");
        FirebaseAuth _user = FirebaseAuth.getInstance();
        _fbuser = _user.getCurrentUser();
    }

    @Override
    public void onClick(View view) {

    }
}
