package uni.fmi.masters.helloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

    Dialog customDialog;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

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

        while(result.length() < length){
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

        customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.custom_dialog);

        preferences = getPreferences(Context.MODE_PRIVATE);
        editor = preferences.edit();

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

        Log.wtf("cheat", hiddenNumber);
        customDialog.hide();
    }

    public void guessTheNumber(View view){

        String playerGuess = guessET.getText().toString();

        String result = calculateCowsAndBulls(playerGuess);
        historyTV.append(result);

        lives--;
        triesTV.setText("Оставащи опити: " + lives);

        if(result.contains("4 Б")){
            endGame(true);
        }

        if(lives <= 0){
          endGame(false);
        }


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

    public void endGame(boolean gameWon){

        guessB.setEnabled(false);

        TextView title = customDialog.findViewById(R.id.customDialogTitleTV);
        TextView content = customDialog.findViewById(R.id.customDialogMessageTV);
        Button cancelB = customDialog.findViewById(R.id.customDialogCancelB);
        Button saveScoreB = customDialog.findViewById(R.id.customDialogSaveNameB);
        EditText nameET = customDialog.findViewById(R.id.customDialogNameET);

        saveScoreB.setVisibility(View.INVISIBLE);
        nameET.setVisibility(View.INVISIBLE);

        if(gameWon){
            int higestScore = preferences.getInt("HigestScore", 0);
            String topUser = preferences.getString("TopUser", "");

            int score = lives * 4;

            title.setText("Поздравления!!!");
            if(score <= higestScore){
                content.setText("След дълги усилия събрахте стадото...\n" +
                        "Но не можахте да достигните висините на нашия топ кравар " + topUser);
            }else{
                content.setText("След дълги усилия събрахте стадото...\n" +
                        "По добър кравар от вас НЕМАААА");

                saveScoreB.setVisibility(View.VISIBLE);
                nameET.setVisibility(View.VISIBLE);
            }

        }else{
            title.setText("Загуба :(");
            content.setText("Кравите избягаха :(");
        }

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.hide();
            }
        });

        customDialog.setCanceledOnTouchOutside(false);

        customDialog.show();
    }

    public void saveTopResult(View view){
        EditText nameET = customDialog.findViewById(R.id.customDialogNameET);
        String name = nameET.getText().toString();
        int score = lives * 4;

        editor.putInt("HigestScore", score);
        editor.putString("TopUser", name);

        editor.apply();
    }

    public void showSettings(View view){

    }
}