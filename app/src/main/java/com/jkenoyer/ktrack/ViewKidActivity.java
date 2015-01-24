package com.jkenoyer.ktrack;

import com.jkenoyer.ktrack.commands.GetCreditReasonsCommand;
import com.jkenoyer.ktrack.commands.GetDemeritReasonsCommand;
import com.jkenoyer.ktrack.commands.GetWithdrawelReasonsCommand;
import com.jkenoyer.ktrack.commands.SaveTransactionCommand;
import com.jkenoyer.ktrack.model.Account;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jkenoyer.ktrack.R;
import com.jkenoyer.ktrack.model.CurrentAccount;
import com.jkenoyer.ktrack.model.TransactionType;

public class ViewKidActivity extends ActionBarActivity {

    private TextView lblName;
    private Button btnCredit;
    private Button btnDemerit;
    private Spinner lstReason;
    private TextView nbrAmount;
    private Button btnSave;
    private Account kid;

    private TransactionType reason = TransactionType.Merit;
    private Button btnWithdrawel;
    private RelativeLayout viewKidActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_kid);

        lblName = (TextView) findViewById(R.id.lblViewKid_Name);
        btnCredit = (Button) findViewById(R.id.lblViewKid_Credit);
        btnDemerit = (Button) findViewById(R.id.lblViewKid_Demerit);
        btnWithdrawel = (Button) findViewById(R.id.lblViewKid_Withdrawel);

        lstReason = (Spinner) findViewById(R.id.lblViewKid_lstReason);
        nbrAmount = (TextView) findViewById(R.id.lblViewKid_nbrAmount);
        btnSave = (Button) findViewById(R.id.lblViewKid_btnSave);

        viewKidActivity = (RelativeLayout) findViewById(R.id.ViewKidActivity);

        kid = (Account) getIntent().getExtras().get("kid");

        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCreditView();
                playSound(R.raw.ding);
            }
        });

        btnDemerit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDemeritView();
                playSound(R.raw.wrong);
            }
        });

        btnWithdrawel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWithdrawelView();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        initView();
    }

    private void playSound(int id) {
        MediaPlayer mp = MediaPlayer.create(this, id);
        mp.start();
    }

    private void save() {

        setEnabled(false);

        Thread thread = new Thread() {
            @Override
            public void run() {

                int amount = getAmount();

                SaveTransactionCommand saveTransactionCommand = new SaveTransactionCommand();
                saveTransactionCommand.save(kid, reason, lstReason.getSelectedItem().toString(), amount);

                finish();
            }
        };

        thread.start();
    }

    private int getAmount() {
        int amount = Integer.parseInt(nbrAmount.getText().toString());

        if(reason != TransactionType.Merit) {
            amount = amount * -1;
        }

        return amount;
    }

    private void setEnabled(boolean enabled) {
        for (int i = 0; i < viewKidActivity.getChildCount(); i++) {
            View child = viewKidActivity.getChildAt(i);
            child.setEnabled(false);
        }
    }

    private void initView() {
        lblName.setText("Choose for " + kid.getName());
        lstReason.setVisibility(View.GONE);
        nbrAmount.setVisibility(View.GONE);
        btnSave.setVisibility(View.GONE);

        nbrAmount.setText("1");
    }

    private void setCreditView() {
        reason = TransactionType.Merit;
        ArrayAdapter creditAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new GetCreditReasonsCommand().getCreditReasons());
        lstReason.setAdapter(creditAdapter);

        setStockVisible();
    }

    private void setDemeritView() {
        ArrayAdapter creditAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new GetDemeritReasonsCommand().getDemeritReasons());
        lstReason.setAdapter(creditAdapter);

        reason = TransactionType.Demerit;
        setStockVisible();
    }

    private void setWithdrawelView() {
        ArrayAdapter creditAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, new GetWithdrawelReasonsCommand().get());
        lstReason.setAdapter(creditAdapter);

        reason = TransactionType.Withdraw;
        setStockVisible();
    }

    private void setStockVisible() {
        lstReason.setVisibility(View.VISIBLE);
        btnCredit.setEnabled(false);
        btnDemerit.setEnabled(false);
        btnWithdrawel.setEnabled(false);
        nbrAmount.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_kid, menu);
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
