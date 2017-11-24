package com.example.colortextgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;




public class GameStart extends AppCompatActivity {

    private static int NUM_TURN = 3;
    public Random r = new Random();
    ColorGameOne[] newGameSet = new ColorGameOne[NUM_TURN];

    int numWrong = 0;
    
    int gameIndex = 0;
    int[] name_but = {R.id.buttonBL, R.id.buttonTR, R.id.buttonTL, R.id.buttonBR};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_start);


        //initialize
        for(int i = 0; i < NUM_TURN; i++){
            newGameSet[i] = new ColorGameOne();
        }


        //set display text
        TextView textView = (TextView) findViewById(R.id.textRight);
        textView.setText(Integer.toString(gameIndex+1));

        TextView textViewWrong = (TextView) findViewById(R.id.textWrong);
        textViewWrong.setText(Integer.toString(numWrong));

        //System.out.println("before Set");
        setButtonColor();
    }

    private void ReDrawTheGame(){
        gameIndex++;
        if(gameIndex >= NUM_TURN)
        {
            System.out.println("before Intent");
            Intent intent2 = new Intent(GameStart.this, EndPage.class);
            startActivity(intent2);
        }

        TextView textView = (TextView) findViewById(R.id.textRight);
        textView.setText(Integer.toString(gameIndex+1));
        //newGameSet[gameIndex].GameOneStart();
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

        if(newGameSet[gameIndex].checkAnswer(i)){
            //mainText.setText("Corrent");
            ReDrawTheGame();
        }
        else{
            numWrong++;
            //mainText.setText("Incorrect");
            wrongText.setText( Integer.toString(numWrong));
        }

        /**
         if(name_but[newGameSet[gameIndex].getAnswerButton()] == but.getId()) {
         newGameSet[gameIndex].checkAnswer(name_but[newGameSet[gameIndex].getAnswerButton()]);
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

            button.setText(newGameSet[gameIndex].getWordList(i));
            //button.setBackgroundColor(newGameSet[gameIndex].getColorArrayButton()[i]);
            //i++;
        }

        //Set the Text show
        TextView textTop = (TextView) findViewById(R.id.answerShow);
        textTop.setText(newGameSet[gameIndex].getWordPrint());
        textTop.setTextColor(newGameSet[gameIndex].getAnswerColor());

    }



}
