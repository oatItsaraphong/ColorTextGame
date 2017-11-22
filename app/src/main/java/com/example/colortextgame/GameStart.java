package com.example.colortextgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameStart extends AppCompatActivity {

    public Random r = new Random();
    ColorGameOne newGameSet = new ColorGameOne();
    int[] name_but = {R.id.buttonBL, R.id.buttonTR, R.id.buttonTL, R.id.buttonBR};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        //Generate the One Game Set
        newGameSet.GameOneStart();
        //GameOne newGameSet = new GameOne();
        //spawn the game board

        //System.out.println("before Set");
        setButtonColor();
    }

    private void ReDrawTheGame(){
        newGameSet.GameOneStart();
        setButtonColor();
    }

    private void buttonClickEvent(TextView mainText, TextView wrongText, int butID){
        Button but = (Button) findViewById(butID);

        //find but.getId() in array name_but[]
        int i = 0;
        for(; i < 4; i++){
            if(name_but[i] == but.getId()){
                break;
            }
        }

        if(newGameSet.checkAnswer(i)){
            //mainText.setText("Corrent");
            ReDrawTheGame();
        }
        else{
            //mainText.setText("Incorrect");
            wrongText.setText( Integer.toString(newGameSet.getWrongAttempt()));
        }

        /**
         if(name_but[newGameSet.getAnswerButton()] == but.getId()) {
         newGameSet.checkAnswer(name_but[newGameSet.getAnswerButton()]);
         mainText.setText("BL");
         }
         else{
         mainText.setText("incorrect---");
         }
         **/
    }
    public void onClickButton(View v) {

        TextView text = (TextView) findViewById(R.id.answerShow);
        TextView textWrong = (TextView) findViewById(R.id.textWrong);

        switch (v.getId()) {
            case  R.id.buttonBL: {
                // do something for button 1 click
                buttonClickEvent(text, textWrong, R.id.buttonBL);
                break;
            }
            case R.id.buttonTL: {
                // do something for button 2 click
                buttonClickEvent(text, textWrong, R.id.buttonTL);
                break;
            }
            case R.id.buttonTR: {
                // do something for button 2 click
                buttonClickEvent(text, textWrong, R.id.buttonTR);
                break;
            }
            case R.id.buttonBR: {
                // do something for button 2 click
                buttonClickEvent(text, textWrong, R.id.buttonBR);
                break;
            }

            //.... etc
        }
    }

    public void setButtonColor(){
        //Set button color & get answer


        //int i = 0;
        for (int i = 0; i < 4 ; i++) {
            Button button = (Button) findViewById(name_but[i]);

            button.setBackgroundColor(newGameSet.getColorArrayButton()[i]);
            //i++;
        }

        //Set the Text show
        TextView textTop = (TextView) findViewById(R.id.answerShow);
        textTop.setText(newGameSet.getWordPrint());
        textTop.setTextColor(newGameSet.getAnswerColor());

    }



}
