package com.jkenoyer.ktrack;

import com.jkenoyer.ktrack.commands.GetAccountFamilyCommand;
import com.jkenoyer.ktrack.commands.ValidateLoginCommand;
import com.jkenoyer.ktrack.commands.ValidationResult;
import com.jkenoyer.ktrack.commands.AccountLoginCommand;
import com.jkenoyer.ktrack.database.Callback;
import com.jkenoyer.ktrack.misc.UncaughtExceptionHandler;
import com.jkenoyer.ktrack.model.Account;
import com.jkenoyer.ktrack.model.CurrentAccount;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ActionBarActivity {

    private TextView txtEmailLogin;
    private TextView txtPassword;
    private TextView txtLoginError;
    private Button btnLogin;
    private Button btnNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(this));

        txtEmailLogin= (TextView) findViewById(R.id.txtEmailLogin);
        txtPassword = (TextView) findViewById(R.id.txtCreatePassword);
        txtLoginError = (TextView) findViewById(R.id.txtLoginError);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });

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

        txtLoginError.setText("");

        validate();

        btnLogin.setEnabled(false);

        new Thread() {
            @Override
            public void run() {

                AccountLoginCommand cmd = new AccountLoginCommand();
                Account success = cmd.Login(txtEmailLogin.getText().toString(), txtPassword.getText().toString());

                if(success == null) {
                    loginFailed();
                    return;
                }

                CurrentAccount.setAccount(success);

                new GetAccountFamilyCommand()
                    .get(success.getFamily(), new Callback<List<Account>>() {

                    @Override
                    public void onResult(List<Account> result) {
                            CurrentAccount.setFamilyMembers(result);
                            navigateToHome();
                    }
                });

            }
        }.start();
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private boolean validate() {

        txtLoginError.setText("");

        ValidationResult validationResult = new ValidateLoginCommand()
                .validate(txtEmailLogin.getText().toString(), txtPassword.getText().toString());

        if (validationResult != null) {
            for(String result: validationResult.getResults()) {
                txtLoginError.setText(txtLoginError.getText().toString() + "\n" + result);
            }
        }

        return validationResult.isSuccess();
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
