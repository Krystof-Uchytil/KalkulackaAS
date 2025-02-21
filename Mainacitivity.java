package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText input1, input2;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Připojení XML prvků k Java kódu
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        result = findViewById(R.id.result);

        Button add = findViewById(R.id.add);
        Button subtract = findViewById(R.id.subtract);
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);
        Button modulo = findViewById(R.id.modulo);
        Button power = findViewById(R.id.power);
        Button root = findViewById(R.id.root);
        Button factorial = findViewById(R.id.factorial);

        // Nastavení funkcí tlačítek
        add.setOnClickListener(view -> calculate("+"));
        subtract.setOnClickListener(view -> calculate("-"));
        multiply.setOnClickListener(view -> calculate("*"));
        divide.setOnClickListener(view -> calculate("/"));
        modulo.setOnClickListener(view -> calculate("%"));
        power.setOnClickListener(view -> calculate("^"));
        root.setOnClickListener(view -> calculate("√"));
        factorial.setOnClickListener(view -> calculate("!"));
    }

    private void calculate(String operation) {
        try {
            double num1 = Double.parseDouble(input1.getText().toString());
            double num2 = 0;
            if (!operation.equals("!")) {
                num2 = Double.parseDouble(input2.getText().toString());
            }

            double output;
            switch (operation) {
                case "+":
                    output = num1 + num2;
                    break;
                case "-":
                    output = num1 - num2;
                    break;
                case "*":
                    output = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        result.setText(R.string.error_divide_by_zero);
                        return;
                    }
                    output = num1 / num2;
                    break;
                case "%":
                    output = num1 % num2;
                    break;
                case "^":
                    output = Math.pow(num1, num2);
                    break;
                case "√":
                    if (num1 < 0) {
                        result.setText(R.string.error_negative_root);
                        return;
                    }
                    output = Math.pow(num1, 1.0 / num2);
                    break;
                case "!":
                    output = factorial((int) num1);
                    break;
                default:
                    result.setText(R.string.error_invalid_input);
                    return;
            }
            result.setText(getString(R.string.result) + " " + output);
        } catch (Exception e) {
            result.setText(R.string.error_invalid_input);
        }
    }

    private double factorial(int n) {
        if (n < 0) return -1;
        double fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
