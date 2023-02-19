package uni.fmi.masters.helloapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class GuessActivity extends AppCompatActivity {

    TextView triesTV;
    TextView hintTV;
    TextView guessTV;
    EditText currentGuessET;
    Button guessB;
    Button restartB;

    Random random = new Random();
    final int LIVES = 6;
    int hiddenNumber;
    int tries;

    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.guessB){
                play();
            }else{
                restartGame();
            }
        }
    };

    private void restartGame(){
        triesTV.setText("Опити 0/" + LIVES);
        hintTV.setText("");
        guessTV.setText("");
        currentGuessET.setText("");

        tries = 0;
        hiddenNumber = random.nextInt(101);

        Log.wtf("cheat", hiddenNumber + "");
        guessB.setEnabled(true);
    }

    private void play(){
        if(currentGuessET.getText().length() == 0)
            return;

        tries++;

        triesTV.setText("Опити " + tries + "/" + LIVES);;
        int playerNumber = Integer.parseInt(currentGuessET.getText().toString());

        guessTV.append(" " + playerNumber);

        if(playerNumber > hiddenNumber){
            hintTV.setText("↓");
        }else if(playerNumber < hiddenNumber){
            hintTV.setText("↑");
        }else{
            new AlertDialog.Builder(GuessActivity.this)
                    .setMessage("Winner winner chichek dinner...").show();
            guessB.setEnabled(false);
            return;
        }

        if(tries >= LIVES){
            new AlertDialog.Builder(GuessActivity.this)
                    .setMessage("HAHAHA LOOOOOSEEEEEER").show();
            guessB.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        triesTV = findViewById(R.id.triesTV);
        hintTV = findViewById(R.id.hintTV);
        guessTV = findViewById(R.id.guessesTV);
        currentGuessET = findViewById(R.id.myGuessET);
        guessB = findViewById(R.id.guessB);
        restartB = findViewById(R.id.restartB);


        guessB.setOnClickListener(onClick);
        restartB.setOnClickListener(onClick);

        restartGame();
    }
}