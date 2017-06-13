package com.policy.ngobeni.policyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ListClients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_clients);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getBaseContext(), Home.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.clients, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView tvPaid = (TextView) findViewById(R.id.tv_paid_clients);
        TextView tvUnPaid = (TextView) findViewById(R.id.tv_outstanding_clients);
        TextView tvAll = (TextView) findViewById(R.id.tv_clients);
        if (id == R.id.paid_clients) {
            tvAll.setVisibility(View.GONE);
            tvPaid.setVisibility(View.VISIBLE);
            tvUnPaid.setVisibility(View.GONE);
            return true;
        } else if (id == R.id.outstanding_clients) {
            tvAll.setVisibility(View.GONE);
            tvPaid.setVisibility(View.GONE);
            tvUnPaid.setVisibility(View.VISIBLE);
            return true;
        } else if (id == R.id.all_clients) {
            tvAll.setVisibility(View.VISIBLE);
            tvPaid.setVisibility(View.GONE);
            tvUnPaid.setVisibility(View.GONE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
