package com.example.colortextgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.InterfaceAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EndPage extends AppCompatActivity {

    private ShareClass share = new ShareClass();
    private int NUM_TURN;
    List<String> resultStr = new ArrayList<>();
    int totalWrong = 0;
    long totalTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);

        NUM_TURN = share.getNUM_TURN();

        //String out;


        for(int i = 0 ; i < NUM_TURN; i++) {
            String inStr = getIntent().getStringExtra(Integer.toString(i));
            String temp = displaySingleResult(inStr, i);
            resultStr.add(temp);

        }
        displayTotal();
        displayOnGrid();


        //Thread t = new Thread(new myConn("ate"));
        //t.start();
        //sendToServer("atand");
        //text.getText();

    }
    private void displayTotal(){
        TextView text = (TextView) findViewById(R.id.textView1);

        text.append("RESULT");
        text.append("\r\n");
        text.append("Total Time: " + (Long.toString(totalTime)));
        text.append("\r\n");
        text.append("Total Wrong: " + (Integer.toString(totalWrong)));
        text.append("\r\n");

    }

    private String displaySingleResult(String oneLine, int index){

        //TextView text = (TextView) findViewById(R.id.textView1);
        List<String> elephantList = Arrays.asList(oneLine.split(","));
        int m = index + 1;
        String out = "    State: " + (Integer.toString(m)) +
                " \r\n" + "Time: " + elephantList.get(10) +
                "\r\n" + "Wrong: " + elephantList.get(7);

        totalTime = totalTime + Long.parseLong(elephantList.get(10));
        totalWrong = totalWrong + Integer.parseInt(elephantList.get(7));

        //text.append(elephantList.get(0));
        //text.append("\r\n");
        return out;
    }

    private void displayOnGrid(){
        GridView gv = (GridView) findViewById(R.id.gridViewResult);
        //TextView tv = (TextView) findViewById(R.id.gridViewResult);

        //final List<String> plantsList = new ArrayList<String>(Arrays.asList(resultStr));
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, resultStr){
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);

                    int color = (Color.LTGRAY); // Transparent
                    view.setBackgroundColor(color);

                    return view;
                    }
        };

        // Data bind GridView with ArrayAdapter (String Array elements)
        gv.setAdapter(gridViewArrayAdapter);
    }

    //Runnable to run the connection thread
    public class myConn implements Runnable{
        String toSend;

        public myConn(String inString){
            toSend = inString;
        }
        @Override
        public void run() {
            System.out.println("connect");
            try{
                Socket soc=new Socket("localhost",2004);
                DataOutputStream dout=new DataOutputStream(soc.getOutputStream());
                dout.writeUTF(toSend);
                dout.flush();
                dout.close();
                soc.close();
            }catch(Exception e){
                e.printStackTrace();}
        }
    }
}

