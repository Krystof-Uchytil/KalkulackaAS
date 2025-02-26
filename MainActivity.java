package com.example.kalkulackadu;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerOperation = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.operator,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerOperation.setAdapter(adapter);
    }

    public void calculate(View view) {
        EditText editText1 = (EditText) findViewById(R.id.editTextNumber);
        EditText editText2 = (EditText) findViewById(R.id.editTextNumber2);
        Spinner spinnerOperation = (Spinner) findViewById(R.id.spinner);
        TextView textView = (TextView) findViewById(R.id.textView);

        String num1Str = editText1.getText().toString();
        String num2Str = editText2.getText().toString();
        String operation = spinnerOperation.getSelectedItem().toString();

        if (num1Str.isEmpty()) {
            textView.setText("Zadejte první číslo");
            return;
        }

        float num1 = Float.parseFloat(num1Str);
        float num2 = 0;
        if (!num2Str.isEmpty()) {
            num2 = Float.parseFloat(num2Str);
        }

        float result;
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    textView.setText("Nelze dělit nulou");
                    return;
                }
                result = num1 / num2;
                break;
            case "modulo":
                if (num2 == 0) {
                    textView.setText("Nelze dělit nulou");
                    return;
                }
                result = num1 % num2;
                break;
            case "mocnina":
                result = (float) Math.pow(num1, num2);
                break;
            case "odmocnina":
                if (num1 < 0) {
                    textView.setText("Nelze odmocnit záporné číslo");
                    return;
                }
                result = (float) Math.sqrt(num1);
                break;
            case "faktorial":
                if (num1 < 0 || num1 % 1 != 0) {
                    textView.setText("Faktoriál lze počítat jen pro nezáporná celá čísla");
                    return;
                }
                result = factorial((int) num1);
                break;
            default:
                textView.setText("Neznámá operace");
                return;
        }

        textView.setText(String.valueOf(result));
    }

    private int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
