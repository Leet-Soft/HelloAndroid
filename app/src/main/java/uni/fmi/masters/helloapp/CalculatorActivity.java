package uni.fmi.masters.helloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    EditText resultET;
    EditText firstNumberET;
    EditText secondNumberET;

    Button plusB;
    Button minusB;
    Button divideB;
    Button multiplyB;

    TextView historyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultET = findViewById(R.id.resultET);
        firstNumberET = findViewById(R.id.firstNumerET);
        secondNumberET = findViewById(R.id.secondNumberET);
        plusB = findViewById(R.id.plusB);
        minusB = findViewById(R.id.minusB);
        divideB = findViewById(R.id.divideB);
        multiplyB = findViewById(R.id.multiplyB);
        historyTV = findViewById(R.id.historyTV);

        plusB.setOnClickListener(onClickListener);
        minusB.setOnClickListener(onClickListener);
        multiplyB.setOnClickListener(onClickListener);
        divideB.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(firstNumberET.getText().length() == 0 ||
                    secondNumberET.getText().length() == 0){
                return;
            }

            String firstNumberString = firstNumberET.getText().toString();
            double firstNumber = Double.parseDouble(firstNumberString);

            double secondNumber = Double.parseDouble(
                    secondNumberET.getText().toString());
            double result = 0;
            String op = "";
            switch (view.getId()){
                case R.id.plusB:
                    result = firstNumber + secondNumber;
                    op = "+";
                    break;
                case R.id.minusB:
                    result = firstNumber - secondNumber;
                    op = "-";
                    break;

                case R.id.multiplyB:
                    result = firstNumber * secondNumber;
                    op = "*";
                    break;

                case R.id.divideB:
                    if(secondNumber == 0){
                        Toast.makeText(CalculatorActivity.this, "Cannot divide by 0",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    result = firstNumber / secondNumber;
                    op = "/";
                    break;
            }

            resultET.setText(result + "");
            //resultET.setText(String.valueOf(result));

            historyTV.append("\n"+ firstNumber + " " + op + " " + secondNumber +
                        " = " + result);


        }
    };
}