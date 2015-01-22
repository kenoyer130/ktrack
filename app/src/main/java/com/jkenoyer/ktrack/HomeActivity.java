package com.jkenoyer.ktrack;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

    private void setKidSelector() {
        for(final Account kid : CurrentAccount.getKids()) {
            Button btn = new Button(getApplicationContext());

            NumberFormat currencyFormatter =
                    NumberFormat.getCurrencyInstance(Locale.ENGLISH);

            btn.setText(kid.getName() + " $" + currencyFormatter.format(kid.getAmount()));

            lstKids.addView(btn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewKid(kid);
                }
            });
        }
    }

    private void viewKid(Account kid) {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        intent.putExtra("role", FamilyRole.Parent);
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
