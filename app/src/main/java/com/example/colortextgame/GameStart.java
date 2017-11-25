package com.example.colortextgame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.Timestamp;
import java.util.Date;
import java.util.Random;




public class GameStart extends AppCompatActivity {

    private static int NUM_TURN = 10;


    public Random r = new Random();
    ColorGameOne[] newGameSet = new ColorGameOne[NUM_TURN];

    int numWrong = 0;
    int gameIndex = 0;

    long appearTime = (new Date().getTime());
    //long reactTime = 0;

    int[] name_but = {R.id.buttonTL, R.id.buttonTR, R.id.buttonBL, R.id.buttonBR};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game_start);


        //initialize
        for(int i = 0; i < NUM_TURN; i++){
            newGameSet[i] = new ColorGameOne();
        }


        //set display text
        textUpdate();

        //System.out.println("before Set");
        setButtonColor();
    }

    private void setTime(){
        long time = (new Date().getTime());

    }

    private void answerTime(){
        long time = (new Date().getTime());
        newGameSet[gameIndex].setReactTime(time - appearTime);
        appearTime = time;

    }

    //Use to update the text Score and Turn
    private void textUpdate(){

        TextView textView = (TextView) findViewById(R.id.textRight);
        TextView textWrong = (TextView) findViewById(R.id.textWrong);

        textView.setText(Integer.toString(gameIndex + 1));
        textWrong.setText(Integer.toString(numWrong));
    }

    //Execute every time the the user answer correctly
    // redraw the game board
    // detect if the game reach the max turn
    private void ReDrawTheGame(){


        gameIndex++;
        if(gameIndex < NUM_TURN) {

            //Update Text
            textUpdate();
            setButtonColor();
        }
        else {
            EndGame();
        }
    }

    //Execute when the game is done
    public void EndGame(){
        if(gameIndex >= NUM_TURN)
        {
            //Start a new intent
            Intent intent = new Intent(this, EndPage.class);

            //put extra
            long time = (new Date().getTime());
            //String test = newGameSet[1].printCSV(new Date().getTime());
            for(int i = 0; i < NUM_TURN; i++){
                intent.putExtra(Integer.toString(i),newGameSet[i].printCSV(time));
            }

            startActivity(intent);
            finish();

        }

    }

    //Execute after the button get click
    // check if the answer is correct
    private void buttonClickEvent(int butID){
        Button but = (Button) findViewById(butID);

        //Find the ide of the correct button
        int i = 0;
        for(; i < 4; i++){
            if(name_but[i] == but.getId()){
                break;
            }
        }

        //check for the right answer
        if(newGameSet[gameIndex].checkAnswer(i)) {
            //set and record time before start new game
            answerTime();
            ReDrawTheGame();
        }
        else{
            numWrong++;
            //mainText.setText("Incorrect");
            textUpdate();
        }
    }

    //Execute when answer button get click
    public void onClickButton(View v) {

        switch (v.getId()) {
            case  R.id.buttonBL: {
                // do something for button 1 click
                this.buttonClickEvent(R.id.buttonBL);
                break;
            }
            case R.id.buttonTL: {
                // do something for button 2 click
                this.buttonClickEvent(R.id.buttonTL);
                break;
            }
            case R.id.buttonTR: {
                // do something for button 2 click
                this.buttonClickEvent(R.id.buttonTR);
                break;
            }
            case R.id.buttonBR: {
                // do something for button 2 click
                this.buttonClickEvent(R.id.buttonBR);
                break;
            }
        }
    }

    public void setButtonColor(){
        //Set button color & get answer

        //int i = 0;
        for (int i = 0; i < 4 ; i++) {
            Button button = (Button) findViewById(name_but[i]);
            button.setText(newGameSet[gameIndex].getWordList(i));

            //this is to set the button to be in color (too easy)
            //button.setBackgroundColor(newGameSet[gameIndex].getColorArrayButton()[i]);
        }

        //Set the Question Text and color
        TextView textTop = (TextView) findViewById(R.id.answerShow);
        textTop.setText(newGameSet[gameIndex].getWordPrint());
        textTop.setTextColor(newGameSet[gameIndex].getAnswerColor());

    }



}
