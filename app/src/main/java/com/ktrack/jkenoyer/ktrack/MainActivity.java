package com.ktrack.jkenoyer.ktrack;

import com.ktrack.jkenoyer.ktrack.database.Ctx;
import com.ktrack.jkenoyer.ktrack.misc.UncaughtExceptionHandler;
import com.ktrack.jkenoyer.ktrack.model.Account;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ktrack.jkenoyer.ktrack.database.AccountDb;


public class MainActivity extends ActionBarActivity {

    private TextView txtUserName;
    private TextView txtPassword;
    private TextView txtLoginError;
    private Button btnLogin;
    private Button btnNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this));

        Ctx.setContext(this);

        txtUserName = (TextView) findViewById(R.id.txtCreateUserName);
        txtPassword = (TextView) findViewById(R.id.txtCreatePassword);
        txtLoginError = (TextView) findViewById(R.id.txtLoginError);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            }
        );

        btnNewAccount = (Button) findViewById(R.id.btnNewAccount);

        btnNewAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                navigateToCreateAccount();
            }
        });
    }

    private void navigateToCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    private void login() {

        btnLogin.setEnabled(false);

        new Thread() {
            @Override
            public void run() {
                AccountDb db = new AccountDb();
                Account success = db.Login(txtUserName.getText().toString(), txtPassword.getText().toString());

                if(success == null) {
                    loginFailed();
              }
            }
        }.start();
    }

    private void loginFailed() {
        txtLoginError.post(new Runnable() {
            @Override
            public void run() {
                txtLoginError.setText("Login failed!");
            }
        });

        btnLogin.post(new Runnable() {
            @Override
            public void run() {
                btnLogin.setEnabled(true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
