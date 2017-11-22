package com.example.colortextgame;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by CordexCoder on 11/22/2017.
 */

public class ColorGameOne {

    private Random r = new Random();

    private HashMap<Integer, Integer> colorList = new HashMap<Integer, Integer>();
    private HashMap<Integer, String> wordList = new HashMap<Integer, String>();

    private int[] colorArrayButton = new int[4];
    private int answerButton;
    private int answerColor;
    private int wrongAttempt = 0;
    private int waitTime = 0;
    private int reactTime = 0;

    private String wordPrint;

    //Ctor
    public ColorGameOne(){
        SetAllColor();
        setFourColor();
        pickAnswer();
    }

    public void GameOneStart(){
        SetAllColor();
        setFourColor();
        pickAnswer();
    }

    //Check answer base on where are the ColorButton
    public boolean checkAnswer(int answerIn){
        if (answerIn == this.answerButton){
            return true;
        }
        else
        {
            this.wrongAttempt++;
            return false;
        }
    }

    //All GET
    public int[] getColorArrayButton(){
        return colorArrayButton;
    }
    public int getAnswerButton(){
        return answerButton;
    }
    public int getAnswerColor(){
        return answerColor;
    }

    public int getReactTime() {
        return reactTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public String getWordPrint() {
        return wordPrint;
    }

    public int getWrongAttempt() {
        return wrongAttempt;
    }


    //ALL SET
    public void setReactTime(int reactTime) {
        this.reactTime = reactTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }


    //PRIVATE
    private void SetAllColor() {
        this.colorList.put(1, Color.RED);
        this.colorList.put(2, Color.BLUE);
        this.colorList.put(3, Color.GREEN);
        this.colorList.put(4, Color.YELLOW);
        this.colorList.put(5, Color.MAGENTA);
        this.colorList.put(6, Color.GRAY);

        this.wordList.put(1,"RED");
        this.wordList.put(2,"BLUE");
        this.wordList.put(3,"GREEN");
        this.wordList.put(4,"YELLOW");
        this.wordList.put(5,"MAGENTA");
        this.wordList.put(6,"GRAY");
        //System.out.println("SetAllColor");
    }

    private void pickAnswer(){
        int k = this.chooseAnswer();
        this.answerColor = this.colorArrayButton[k];
        this.answerButton = k;

        //random word out

        //this.wordPrint = this.wordList.get(this.chooseAnswer());
    }


    private void setFourColor() {
        int flag = 0;
        int color;
        int setText = chooseAnswer();
        while (true) {
            color = randomColor();
            //check repeat colors
            int set = 0;
            //System.out.println("suze" + this.colorArrayButton.length);
            for(int i = 0; i < this.colorArrayButton.length ; i++){
                if(this.colorArrayButton[i] == this.colorList.get(color)){
                    set++;
                }
            }

            //if not repeat add the colort
            if(set == 0){
                this.colorArrayButton[flag] = this.colorList.get(color);
                //ystem.out.println("pickColorBreak" + this.colorList.get(color));

                if(setText == flag){
                    this.wordPrint = this.wordList.get(color);
                }
                flag++;
            }


            //break
            if (flag >= 4) {
                break;
            }

        }
        //System.out.println("pickAnswer");
        return;
    }

    private int chooseAnswer(){
        int max = colorArrayButton.length - 1;
        int min = 0;
        //Seed
        //Random r = new Random();
        return r.nextInt(max - min + 1) + min;

    }

    private int randomColor(){
        int max = colorList.size();

        int min = 1;
        //Seed
        //Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}
