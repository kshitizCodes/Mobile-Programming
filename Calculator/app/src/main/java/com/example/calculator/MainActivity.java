package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private double currentValue = 0;
    private String currentOperator = "";
    private boolean isNewOperator = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        setDigitButtonClickListener(R.id.btn_0, "0");
        setDigitButtonClickListener(R.id.btn_1, "1");
        setDigitButtonClickListener(R.id.btn_2, "2");
        setDigitButtonClickListener(R.id.btn_3, "3");
        setDigitButtonClickListener(R.id.btn_4, "4");
        setDigitButtonClickListener(R.id.btn_5, "5");
        setDigitButtonClickListener(R.id.btn_6, "6");
        setDigitButtonClickListener(R.id.btn_7, "7");
        setDigitButtonClickListener(R.id.btn_8, "8");
        setDigitButtonClickListener(R.id.btn_9, "9");
        setOperatorButtonClickListener(R.id.btn_add, "+");
        setOperatorButtonClickListener(R.id.btn_subtract, "-");
        setOperatorButtonClickListener(R.id.btn_multiply, "*");
        setOperatorButtonClickListener(R.id.btn_divide, "/");
        Button btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(v -> clear());
        Button btnEqual = findViewById(R.id.btn_equal);
        btnEqual.setOnClickListener(v -> calculate());
    }

    private void setDigitButtonClickListener(int buttonId, final String digit) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> appendDigit(digit));
    }

    private void setOperatorButtonClickListener(int buttonId, final String operator) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> setOperator(operator));
    }

    private void appendDigit(String digit) {
        if (isNewOperator) {
            editText.setText(digit);
            isNewOperator = false;
        } else {
            editText.append(digit);
        }
    }

    private void setOperator(String operator) {
        currentValue = Double.parseDouble(editText.getText().toString());
        currentOperator = operator;
        isNewOperator = true;
    }

    private void calculate() {
        double newValue = Double.parseDouble(editText.getText().toString());
        if (currentOperator.equals("+")) currentValue += newValue;
        if (currentOperator.equals("-")) currentValue -= newValue;
        if (currentOperator.equals("*")) currentValue *= newValue;
        if (currentOperator.equals("/")) currentValue /= newValue;
        editText.setText(String.valueOf(currentValue));
        currentOperator = "";
        isNewOperator = true;
    }

    private void clear() {
        editText.setText("0");
        currentValue = 0;
        currentOperator = "";
        isNewOperator = true;
    }
}
