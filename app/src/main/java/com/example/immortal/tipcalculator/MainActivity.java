package com.example.immortal.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String BILL_TOTAL = "BILL_TOTAL";
    private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

    private double currentbilltotal;
    private int currentcustompercent;
    private EditText tip10Edittext;
    private EditText total10Edittext;
    private EditText tip15Edittext;
    private EditText total15Edittext;
    private EditText billedittext;
    private EditText tip20Edittext;
    private EditText total20Edittext;
    private TextView customtiptextview;
    private EditText tipcustomedittext;
    private EditText totalcustomedittext;
    private SeekBar customSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tip10Edittext = (EditText) findViewById(R.id.tip10EditText);
        tip15Edittext = (EditText) findViewById(R.id.tip15EditText);
        tip20Edittext = (EditText) findViewById(R.id.tip20EditText);
        total10Edittext = (EditText) findViewById(R.id.total10EditText);
        total15Edittext = (EditText) findViewById(R.id.total15EditText);
        total20Edittext = (EditText) findViewById(R.id.total20EditText);
        billedittext = (EditText) findViewById(R.id.billEditText);
        customtiptextview = (TextView) findViewById(R.id.customTipTextView);
        tipcustomedittext = (EditText) findViewById(R.id.tipCustomEditText);
        totalcustomedittext = (EditText) findViewById(R.id.totalCustomEditText);
        customSeekBar = (SeekBar) findViewById(R.id.customSeekBar);

        billedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    currentbilltotal = Double.parseDouble(s.toString());
                }catch (NumberFormatException e){
                    currentbilltotal = 0.0;
                }
                updateBill();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        customSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentcustompercent = seekBar.getProgress();
                updateCustomBill();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void updateBill(){
        double tenPercentTip = currentbilltotal * 0.1;
        double tenPercentTotal = currentbilltotal + tenPercentTip;

        tip10Edittext.setText(String.format(" %.02f", tenPercentTip));
        total10Edittext.setText(String.format(" %.02f", tenPercentTotal));

        double fifteenPercentTip = currentbilltotal * 0.15;
        double fifteenPercentTotal = currentbilltotal + fifteenPercentTip;

        tip15Edittext.setText(String.format(" %.02f", fifteenPercentTip));
        total15Edittext.setText(String.format(" %.02f", fifteenPercentTotal));

        double twentyPercentTip = currentbilltotal * 0.20;
        double twentyPercentTotal = currentbilltotal + twentyPercentTip;

        tip20Edittext.setText(String.format(" %.02f", twentyPercentTip));
        total20Edittext.setText(String.format(" %.02f", twentyPercentTotal));
    }

    protected void updateCustomBill(){
        double currentPercentTip = currentbilltotal * currentcustompercent * 0.01;
        double currentPercentTotal = currentbilltotal + currentPercentTip;

        customtiptextview.setText(currentcustompercent + " %");
        tipcustomedittext.setText(String.format(" %.02f", currentPercentTip));
        totalcustomedittext.setText(String.format(" %.02f", currentPercentTotal));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        currentbilltotal = savedInstanceState.getDouble(BILL_TOTAL);
        currentcustompercent = savedInstanceState.getInt(CUSTOM_PERCENT);

        updateBill();
        updateCustomBill();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(BILL_TOTAL, currentbilltotal);
        outState.putInt(CUSTOM_PERCENT, currentcustompercent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.item1:
                startActivity(new Intent(this, calculator.class));
                return true;
            case R.id.item2:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.item3:
                startActivity(new Intent(this, Percent_calculate.class));
                return true;
            case R.id.item4:
                onBackPressed();
                return true;
            case R.id.item5:
                startActivity(new Intent(this, pick_number.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
