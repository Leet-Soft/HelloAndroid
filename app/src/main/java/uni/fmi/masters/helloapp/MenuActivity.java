package uni.fmi.masters.helloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    Button helloB;
    Button calculatorB;
    Button guessB;

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;

            switch (view.getId()){
                case R.id.menuCalculatorB:
                    intent = new Intent(MenuActivity.this, CalculatorActivity.class);
                    break;
                case R.id.menuHelloB:
                    intent = new Intent(MenuActivity.this, MainActivity.class);
                    break;
                case R.id.menuGuessB:
                    intent = new Intent(MenuActivity.this, GuessActivity.class);
                    break;
            }

            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        helloB = findViewById(R.id.menuHelloB);
        calculatorB = findViewById(R.id.menuCalculatorB);
        guessB = findViewById(R.id.menuGuessB);

        helloB.setOnClickListener(onClick);
        calculatorB.setOnClickListener(onClick);
        guessB.setOnClickListener(onClick);
    }
}