package uni.fmi.masters.helloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button helloB;
    Button calculatorB;
    Button guessB;
    Button cowsB;
    Button cameraB;
    Button shoppingListB;
    Button randomB;

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
                case R.id.menuCowsB:
                    intent = new Intent(MenuActivity.this, CowsAndBullsActivity.class);
                    break;
                case R.id.menuCameraB:
                    intent = new Intent(MenuActivity.this, PlayingWithDeviceActivity.class);
                    break;
                case R.id.menuShoppingListB:
                    intent = new Intent(MenuActivity.this, ShoppingListActivity.class);
                    break;
                case R.id.menuRandomButton:
                    intent = new Intent(MenuActivity.this, RandomButtonActivity.class);
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
        cowsB = findViewById(R.id.menuCowsB);
        cameraB = findViewById(R.id.menuCameraB);
        shoppingListB = findViewById(R.id.menuShoppingListB);
        randomB = findViewById(R.id.menuRandomButton);

        helloB.setOnClickListener(onClick);
        calculatorB.setOnClickListener(onClick);
        guessB.setOnClickListener(onClick);
        cowsB.setOnClickListener(onClick);
        cameraB.setOnClickListener(onClick);
        shoppingListB.setOnClickListener(onClick);
        randomB.setOnClickListener(onClick);
    }
}