package com.example.immortal.tipcalculator;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class calculator extends AppCompatActivity {
    EditText editText;
    Button count;
    Button divide;
    Button multiply;
    Button minus;
    Button plus;
    Button square;
    Button elevate;

    private static final String EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE";

    double first_number = 0.0;
    double second_number = 0.0;
    double result = 0.0;
    String operation = "-";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        count = (Button) findViewById(R.id.count);
        divide = (Button) findViewById(R.id.divide);
        multiply = (Button) findViewById(R.id.multiply);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        square = (Button) findViewById(R.id.square);
        elevate = (Button) findViewById(R.id.elevate);

        editText = (EditText) findViewById(R.id.editText);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.minus:
                        operation = "-";
                        first_number = Double.parseDouble(editText.getText().toString());
                        editText.setText("");
                        break;
                    case R.id.divide:
                        operation = "/";
                        first_number = Double.parseDouble(editText.getText().toString());
                        editText.setText("");
                        break;
                    case R.id.multiply:
                        operation = "*";
                        first_number = Double.parseDouble(editText.getText().toString());
                        editText.setText("");
                        break;
                    case R.id.plus:
                        operation = "+";
                        first_number = Double.parseDouble(editText.getText().toString());
                        editText.setText("");
                        break;
                    case R.id.square:
                        operation = "√";
                        first_number = Double.parseDouble(editText.getText().toString());
                        if(first_number < 0){
                            Toast.makeText(calculator.this, "Число від'ємне", Toast.LENGTH_LONG).show();
                            break;
                        }
                        result = Math.sqrt(first_number);
                        editText.setText(String.format(Locale.US, "%.02f", result));
                        break;
                    case R.id.elevate:
                        operation = "^";
                        first_number = Double.parseDouble(editText.getText().toString());
                        editText.setText("");
                        break;
                    case R.id.count:
                        second_number = Double.parseDouble(editText.getText().toString());
                        switch (operation){
                            case "+":
                                result = first_number + second_number;
                                editText.setText(String.format(Locale.US, "%.02f", result));
                                break;
                            case "-":
                                result = first_number - second_number;
                                editText.setText(String.format(Locale.US, "%.02f", result));
                                break;
                            case "/":
                                if(second_number == 0){
                                    Toast.makeText(calculator.this, "Ділення на 0", Toast.LENGTH_LONG).show();
                                    break;
                                }
                                result = first_number / second_number;
                                editText.setText(String.format(Locale.US, "%.02f", result));
                                break;
                            case "*":
                                result = first_number * second_number;
                                editText.setText(String.format(Locale.US, "%.02f", result));
                                break;
                            case "^":
                                result = Math.pow(first_number, second_number);
                                editText.setText(String.format(Locale.US, "%.02f", result));
                                break;
                        }
                        break;
                }
            }
        };

        count.setOnClickListener(onClickListener);
        divide.setOnClickListener(onClickListener);
        multiply.setOnClickListener(onClickListener);
        minus.setOnClickListener(onClickListener);
        plus.setOnClickListener(onClickListener);
        square.setOnClickListener(onClickListener);
        elevate.setOnClickListener(onClickListener);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editText.setText(savedInstanceState.getInt(EDIT_TEXT_VALUE));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDIT_TEXT_VALUE, editText.getText().toString());
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
