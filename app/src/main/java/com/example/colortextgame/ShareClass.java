package com.example.colortextgame;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by CordexCoder on 11/25/2017.
 */

public class ShareClass {
    private int NUM_TURN = 2;

    private HashMap<Integer, Integer> colorList = new HashMap<Integer, Integer>();
    private HashMap<Integer, String> wordList = new HashMap<Integer, String>();

    public ShareClass(){
        SetAllColor();
    }

    public int getNUM_TURN(){ return NUM_TURN;}
    public int getColorList(int key){
        return this.colorList.get(key);
    }
    public String getWordList(int key){
        return this.wordList.get(key);
    }
    public int getColorListSize(){
        return this.colorList.size();
    }

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
}
