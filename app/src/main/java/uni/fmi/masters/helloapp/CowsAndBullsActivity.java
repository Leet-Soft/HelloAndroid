package uni.fmi.masters.helloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class CowsAndBullsActivity extends AppCompatActivity {

    Random random = new Random();
    TextView hiddenNumberTV;
    EditText guessET;
    TextView triesTV;
    Button guessB;
    TextView historyTV;

    String hiddenNumber;
    int lives = 10;

    /**
     *
     * @param length
     * @param uniqueLettersOnly
     * @return
     * @throws Exception
     */
    public String generateNumber(int length, boolean uniqueLettersOnly) throws Exception {
        if(length > 9)
            throw new Exception("Cannot be more then 9 length");

        String result = "";

        String num = random.nextInt(10) + "";
        result += num;

        while(result.length() <= length){
            num = random.nextInt(10) + "";

            if(!uniqueLettersOnly || !result.contains(num)){
                result += num;
            }
        }
        return result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cows_and_bulls);

        hiddenNumberTV = findViewById(R.id.hiddenNumberTV);
        guessET = findViewById(R.id.playerGuessET);
        triesTV = findViewById(R.id.livesTV);
        guessB = findViewById(R.id.playB);
        historyTV = findViewById(R.id.numbersHistoryTV);

        try {
            restartGame(null);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void restartGame(View view) throws Exception {
        lives = 10;

        hiddenNumberTV.setText("XXXX");
        guessET.setText("");
        triesTV.setText(lives + "");
        hiddenNumber = generateNumber(4, true);
        historyTV.setText("");
    }

    public void guessTheNumber(View view){

        String playerGuess = guessET.getText().toString();

        historyTV.append(calculateCowsAndBulls(playerGuess));

        lives--;
        triesTV.setText("Оставащи опити: " + lives);

        if(lives <= 0)
            guessB.setEnabled(false);

    }

    public String calculateCowsAndBulls(String number){

        int bulls = 0;
        int cows = 0;

        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) == hiddenNumber.charAt(i)){
                bulls++;
            }else if(hiddenNumber.contains(number.charAt(i) + "")){
                cows++;
            }
        }

        return number + " - " + bulls + " Б, " + cows + " K\n";
    }
}