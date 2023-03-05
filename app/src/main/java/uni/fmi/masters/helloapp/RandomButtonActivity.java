package uni.fmi.masters.helloapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class RandomButtonActivity extends AppCompatActivity {

    ConstraintLayout mainCL;
    TextView scoreTV;
    Button startB;

    int score = 0;
    long startTime;
    int screenWidth;
    int screenHeight;

    Random random = new Random();

    Button clickMeButton;
    boolean firstMove = true;

    View.OnClickListener onStartClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MoveButton();
        }
    };

    private void MoveButton() {

        int x = random.nextInt(screenWidth - clickMeButton.getWidth() - 50);
        int y = random.nextInt(screenHeight - clickMeButton.getHeight() - 50);

        ConstraintSet set = new ConstraintSet();

        set.clone(mainCL);
        set.connect(clickMeButton.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, y);
        set.connect(clickMeButton.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, x);
        set.applyTo(mainCL);

        if(firstMove){
            startTime = System.currentTimeMillis();
            startB.setVisibility(View.GONE);
            clickMeButton.setVisibility(View.VISIBLE);

            firstMove = false;
            return;
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        score += 2000 - elapsedTime;

        startTime = System.currentTimeMillis();

        scoreTV.setText("Score :" + score);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_button);

        mainCL = findViewById(R.id.mainCL);
        scoreTV = findViewById(R.id.scoreTV);
        startB = findViewById(R.id.startGameB);

        startB.setOnClickListener(onStartClicked);

        clickMeButton = new Button(this);
        clickMeButton.setText("Click Me!");
        clickMeButton.setOnClickListener(onStartClicked);

        DisplayMetrics display = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(display);

        screenWidth = display.widthPixels;
        screenHeight = display.heightPixels;

        clickMeButton.setId(View.generateViewId());
        mainCL.addView(clickMeButton);

        clickMeButton.setVisibility(View.GONE);
    }
}