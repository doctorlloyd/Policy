package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.policy.ngobeni.policyapp.pojos.Member;

import static com.policy.ngobeni.policyapp.R.id.btnAddDependent;
import static com.policy.ngobeni.policyapp.R.id.btnUpdate;

public class Dependent extends AppCompatActivity implements View.OnClickListener{
    private String _key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dependent_registration);
        initialize();

        _key = getIntent().getStringExtra("_key");

    }

    void initialize(){
        Button btnDone = (Button) findViewById(R.id.btnDoneDependent);
        btnDone.setOnClickListener(this);
        Button btnAddDependent = (Button) findViewById(R.id.btnRegisterDependent);
        btnAddDependent.setOnClickListener(this);
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
//                Member member = new Member();
                break;
            case R.id.btnDoneDependent:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
