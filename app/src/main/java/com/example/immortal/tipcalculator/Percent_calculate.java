package com.example.immortal.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class Percent_calculate extends AppCompatActivity {
    EditText number;
    SeekBar seekBar;
    TextView result;

    private static final String NUMBER_STATE = "NUMBER";
    private static final String PERCENT_STATE = "PERCENT";

    double current_number = 0.0;
    int current_percent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_percent_calculate);

        number = (EditText) findViewById(R.id.number);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        result = (TextView) findViewById(R.id.result);

        number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    current_number = Double.parseDouble(s.toString());
                }catch (NumberFormatException e){
                    current_number = 0.0;
                }
                count_result();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                current_percent = seekBar.getProgress();
                count_result();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void count_result(){
        double percent = current_number * current_percent * 0.01;
        result.setText(String.format(Locale.US, current_percent + "%s від %.02f -  %.02f", "%", current_number, percent));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        current_number = savedInstanceState.getDouble(NUMBER_STATE);
        current_percent = savedInstanceState.getInt(PERCENT_STATE);
        count_result();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(NUMBER_STATE, current_number);
        outState.putInt(PERCENT_STATE, current_percent);
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
