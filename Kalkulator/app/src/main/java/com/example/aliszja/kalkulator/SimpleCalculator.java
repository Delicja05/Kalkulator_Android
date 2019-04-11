package com.example.aliszja.kalkulator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCalculator extends AppCompatActivity {

    private static final String TAG = "Equation";
    private TextView tv_equation;
    private TextView tv_result;
    String first = "";
    String second = "";
    String operator = "";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("first", first );
        outState.putString("second", second);
        outState.putString("operator", operator);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);

        tv_equation = findViewById(R.id.TextViewEquation);
        tv_result = findViewById(R.id.TextViewResult);

        if (savedInstanceState != null) {
            first = savedInstanceState.getString("first");
            second = savedInstanceState.getString("second");
            operator = savedInstanceState.getString("operator");

            if(!first.equals("") && second.equals("")){
                tv_equation.setText(catValue(first).concat(operator));
            }else{
                tv_equation.setText(catValue(first).concat(operator).concat(catValue(second)));
                calculate();
            }
        }

        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button0:
                        setAndShow(String.valueOf(0));
                        break;
                    case R.id.button1:
                        setAndShow(String.valueOf(1));
                        break;
                    case R.id.button2:
                        setAndShow(String.valueOf(2));
                        break;
                    case R.id.button3:
                        setAndShow(String.valueOf(3));
                        break;
                    case R.id.button4:
                        setAndShow(String.valueOf(4));
                        break;
                    case R.id.button5:
                        setAndShow(String.valueOf(5));
                        break;
                    case R.id.button6:
                        setAndShow(String.valueOf(6));
                        break;
                    case R.id.button7:
                        setAndShow(String.valueOf(7));
                        break;
                    case R.id.button8:
                        setAndShow(String.valueOf(8));
                        break;
                    case R.id.button9:
                        setAndShow(String.valueOf(9));
                        break;
                    case R.id.buttonPlus:
                        setOperator("+");
                        break;
                    case R.id.buttonMinus:
                        setOperator("-");
                        break;
                    case R.id.buttonMultiply:
                        setOperator("*");
                        break;
                    case R.id.buttonDivide:
                        setOperator("/");
                        break;
                    case R.id.buttonEquals:
                        calculate();
                        break;
                    case R.id.buttonComma:
                        setAndShow(String.valueOf("."));
                        break;
                    case R.id.buttonC:
                        clearOne();
                        break;
                    case R.id.buttonAC:
                        clearAll();
                        break;
                    case R.id.buttonPlusMinus:
                        setAndShow(String.valueOf("+/-"));
                        break;

                    default:
                        break;
                }
                Log.d(TAG, "first: " + first + "\t operator: " + operator + "\t second: " + second);
            }
        };

        findViewById(R.id.button0).setOnClickListener(handler);
        findViewById(R.id.button1).setOnClickListener(handler);
        findViewById(R.id.button2).setOnClickListener(handler);
        findViewById(R.id.button3).setOnClickListener(handler);
        findViewById(R.id.button4).setOnClickListener(handler);
        findViewById(R.id.button5).setOnClickListener(handler);
        findViewById(R.id.button6).setOnClickListener(handler);
        findViewById(R.id.button7).setOnClickListener(handler);
        findViewById(R.id.button8).setOnClickListener(handler);
        findViewById(R.id.button9).setOnClickListener(handler);
        findViewById(R.id.buttonPlus).setOnClickListener(handler);
        findViewById(R.id.buttonMinus).setOnClickListener(handler);
        findViewById(R.id.buttonMultiply).setOnClickListener(handler);
        findViewById(R.id.buttonDivide).setOnClickListener(handler);
        findViewById(R.id.buttonEquals).setOnClickListener(handler);
        findViewById(R.id.buttonComma).setOnClickListener(handler);
        findViewById(R.id.buttonC).setOnClickListener(handler);
        findViewById(R.id.buttonAC).setOnClickListener(handler);
        findViewById(R.id.buttonPlusMinus).setOnClickListener(handler);
    }

    public void setOperator(String op){
        if(tv_result.getText()!="" && !operator.equals("")){
            first = String.valueOf(tv_result.getText());
            second = "";
        }

        if(tv_equation.getText().equals("")){
            tv_equation.setText("");
        }
        else{
            operator = String.valueOf(op);
            tv_equation.setText(first.concat(operator));
        }
    }

    public void setAndShow(String string) {
        String variable = operator.equals("") ? first : second;

        if(string.equals("+/-")) {
            if (variable.contains("-")) {
                variable = variable.substring(1);
            }
            else {
                if(variable.equals(first)){
                    variable = "-" + variable;
                }
                else if(first.contains("-")){
                    first = first.substring(1);
                }
                else {
                    first = "-" + first;
                }
            }
        }
        else if(!string.equals(".") || !variable.contains(".")){
            variable = variable.concat(string);
        }

        if (operator.equals("")){
            first = variable;
        }
        else {
            second = variable;
            variable = first+operator+second;
        }

        tv_equation.setText(variable);
    }

    public String catValue(String value){
        if(value.length() <= 1)
            return value;

        while (value.charAt(value.length()-1) == '0' || value.charAt(value.length()-1) == '.'){
            if(value.charAt(value.length()-1) == '.'){
                value = value.substring(0, value.length() - 1);
                break;
            }
            else {
                value = value.substring(0, value.length() - 1);
            }
        }
        return value;
    }

    @SuppressLint("DefaultLocale")
    public void calculate(){
        String calculateResult = "";
        switch (operator){
            case "+":
                calculateResult = String.format("%.6f", ((Double.parseDouble(first)) + Double.parseDouble(second)));
                break;
            case "-":
                calculateResult = String.format("%.6f", ((Double.parseDouble(first)) - Double.parseDouble(second)));
                break;
            case "*":
                calculateResult = String.format("%.6f", ((Double.parseDouble(first)) * Double.parseDouble(second)));
                break;
            case "/":
                if(Double.parseDouble(second) == 0){
                    Toast.makeText(this, "Nie dziel przez zero!", Toast.LENGTH_LONG).show();
                    clearAll();
                }else{
                    calculateResult = String.format("%.6f", ((Double.parseDouble(first)) / Double.parseDouble(second)));
                }
                break;
            default:
                break;
        }

        if(!calculateResult.equals("")){
            tv_result.setText(catValue(calculateResult));
        }
    }

    public void clearAll(){
        first = "";
        second = "";
        operator = "";
        tv_equation.setText("");
        tv_result.setText("");
    }

    public void clearOne(){
        if(tv_equation.getText().equals("")){
            tv_equation.setText("");
        }
        else {
            tv_equation.setText(tv_equation.getText().subSequence(0,tv_equation.length()-1));
        }

        if(!second.equals("")){
            second = String.valueOf(second.subSequence(0,second.length()-1));
        }
        else if(!operator.equals("")){
            operator = String.valueOf(operator.subSequence(0,operator.length()-1));
        }
        else if(!first.equals("")){
            first = String.valueOf(first.subSequence(0,first.length()-1));
        }
    }
}
