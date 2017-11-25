package com.example.colortextgame;

import android.graphics.Color;
import android.text.TextUtils;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Created by CordexCoder on 11/22/2017.
 */

public class ColorGameOne {

    private Random r = new Random();
    private ShareClass share = new ShareClass();

    private HashMap<Integer, Integer>  = new HashMap<Integer, Integer>();
    private HashMap<Integer, String> wordList = new HashMap<Integer, String>();

    private int[] colorArrayButton = new int[4];
    private String[] colorArrayWord = new String[4];
    private int answerButton;
    private int answerColor;
    private int answerColorKey;
    private int wrongAttempt = 0;
    private long waitTime = 0;
    private long reactTime = 0;

    private String wordPrint;

    //for CSV
    private int[] colorArrayIndex = new int[4];
    private int wordPrintIndex;

    //Ctor
    public ColorGameOne(){
        SetAllColor();
        setFourColor();
        pickAnswer();
    }

    //Need to be remove later-------------------------
    public void GameOneStart(){
        SetAllColor();
        //System.out.println("before Set5");
        setFourColor();
        //System.out.println("before Set6");
        pickAnswer();
        //System.out.println("before Set7");
    }
    //-------------------------------------------------

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
    public String getWordList(int key){
        return this.colorArrayWord[key];
    }
    public int getColorArrayButton(int key){
        return this.colorArrayButton[key];
    }
    public int getAnswerButton(){
        return answerButton;
    }
    public int getAnswerColor(){
        return answerColor;
    }
    public long getReactTime() {
        return reactTime;
    }
    public long getWaitTime() {
        return waitTime;
    }
    public String getWordPrint() {
        return wordPrint;
    }
    public int getWrongAttempt() {
        return wrongAttempt;
    }


    //ALL SET
    public void setReactTime(long reactTime) {
        this.reactTime = reactTime;
    }
    public void setWaitTime(long waitTime) {
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

        this.answerColor = this.colorList.get(this.colorArrayButton[k]);
        //this.answerColor = this.colorArrayButton[k];
        this.answerColorKey = this.colorArrayButton[k];
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
                if(this.colorArrayButton[i] == color){
                //if(this.colorArrayButton[i] == this.colorList.get(color)){
                    set++;
                }
            }

            //if not repeat add the colort
            if(set == 0){
                //this.colorArrayButton[flag] = this.colorList.get(color);
                this.colorArrayButton[flag] = color;
                this.colorArrayWord[flag] = this.wordList.get(color);


                //csv
                this.colorArrayIndex[flag] = color;
                //ystem.out.println("pickColorBreak" + this.colorList.get(color));

                if(setText == flag){
                    this.wordPrint = this.wordList.get(color);


                    //csv
                    this.wordPrintIndex = color;
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

    public String printCSV(long time){
        List<String> mainString = new LinkedList<String>();
        mainString.add(Long.toString(time));

        mainString.add(Integer.toString(this.colorArrayIndex[0]));
        mainString.add(Integer.toString(this.colorArrayIndex[1]));
        mainString.add(Integer.toString(this.colorArrayIndex[2]));
        mainString.add(Integer.toString(this.colorArrayIndex[3]));

        mainString.add(Integer.toString(this.wordPrintIndex));
        mainString.add(Integer.toString(this.answerColorKey));
        mainString.add(Integer.toString(this.answerButton));

        mainString.add(Integer.toString(this.wrongAttempt));
        mainString.add(Long.toString(this.waitTime));
        mainString.add(Long.toString(this.reactTime));

        String out = TextUtils.join(",", mainString);
        return out;

        /**
         CSV out format
         Timestamp in long
         4** color keys appear on the 4 button
         Color keys(name) quiz text written out
         Color keys(int) quiz text of the color (answer color key)
         Button number index 0-3
         Wrong attempt
         Wait time
         React time
         */


    }
}
