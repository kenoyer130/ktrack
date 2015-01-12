package com.ktrack.jkenoyer.ktrack;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ktrack.jkenoyer.ktrack.database.AccountDb;
import com.ktrack.jkenoyer.ktrack.model.Account;


public class CreateAccountActivity extends ActionBarActivity {

    private static final int MAX_PASSWORD_LENGTH = 1;

    private EditText txtCreateUserName;
    private EditText txtCreatePassword;
    private EditText txtCreateConfirm;
    private TextView txtCreateError;
    private Button btnNewAccount;
    private EditText txtFamily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        txtCreateUserName = (EditText) findViewById(R.id.txtCreateUserName);
        txtFamily = (EditText) findViewById(R.id.txtFamily);
        txtCreatePassword = (EditText) findViewById(R.id.txtCreatePassword);
        txtCreateConfirm = (EditText) findViewById(R.id.txtCreateConfirm);
        txtCreateError = (TextView) findViewById(R.id.txtCreateError);
        btnNewAccount = (Button) findViewById(R.id.btnCreateAccount);

        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {

        if(!validate()) {
            return;
        }

        btnNewAccount.setEnabled(false);

        new Thread() {
            @Override
            public void run() {
                AccountDb db = new AccountDb();
                AccountDb.CreateAccountResult result = db.CreateAccount(
                        txtCreateUserName.getText().toString(),
                        txtFamily.getText().toString(),
                        txtCreatePassword.getText().toString());

                if(!result.Success) {

                    txtCreateError.setText(result.error);

                    btnNewAccount.post(new Runnable() {
                        @Override
                        public void run() {
                            btnNewAccount.setEnabled(true);
                        }
                    });

                    return;
                }
            }
        }.start();
    }

    private boolean validate() {

        if(txtCreateUserName.getText().toString().equals("") ||
                txtFamily.getText().toString().equals("") ||
                txtCreatePassword.getText().toString().equals("") ||
                txtCreateConfirm.getText().toString().equals("")
                ) {

            txtCreateError.setText("All fields are required!");

            return false;
        }

        if(!txtCreatePassword.getText().toString().equals(txtCreateConfirm.getText().toString())) {
            txtCreateError.setText("Password and confirm password must match!");
            return false;
        }

        if(txtCreatePassword.getText().length() < MAX_PASSWORD_LENGTH) {
            txtCreateError.setText("Password must be greater then 6 characters.");
            return false;
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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
