package com.jkenoyer.ktrack;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.jkenoyer.ktrack.R;
import com.jkenoyer.ktrack.model.FamilyRole;

public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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

            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("role", FamilyRole.Child);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
