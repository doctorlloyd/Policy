package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private Button btnSignIn,btnCancel,btnRegister;
    private LinearLayout loginLayout, registerLayout;
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
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);
        registerLayout = (LinearLayout) findViewById(R.id.register_layout);
        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
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
}
