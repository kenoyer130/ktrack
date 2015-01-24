package com.jkenoyer.ktrack;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jkenoyer.ktrack.model.Account;

import com.jkenoyer.ktrack.R;
import com.jkenoyer.ktrack.model.CurrentAccount;
import com.jkenoyer.ktrack.model.FamilyRole;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class HomeActivity extends ActionBarActivity {

    private ListView lstKids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        lstKids = (ListView) findViewById(R.id.lstKids);

        setKidSelector();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter adapter = (ArrayAdapter) lstKids.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private void setKidSelector() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, CurrentAccount.getKids());
        lstKids.setAdapter(adapter);

        lstKids.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                Account selectedAccount =(Account) (lstKids.getItemAtPosition(myItemInt));
                navigateToKid(selectedAccount);
            }
        });
    }

    private void navigateToKid(Account kid) {
        Intent intent = new Intent(this, ViewKidActivity.class);
        intent.putExtra("kid", kid);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.menu_add_parent) {
            Intent intent = new Intent(this, CreateAccountActivity.class);
            intent.putExtra("role", FamilyRole.Parent);
            startActivity(intent);
        }

        if(id == R.id.menu_add_child) {

            Intent intent = new Intent(this, CreateAccountActivity.class);
            intent.putExtra("role", FamilyRole.Child);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
