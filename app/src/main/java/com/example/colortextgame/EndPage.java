package com.example.colortextgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.net.Socket;

public class EndPage extends AppCompatActivity {

    private static int NUM_TURN = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);

        //String out;
        TextView text = (TextView) findViewById(R.id.textView1);
        for(int i = 0 ; i < NUM_TURN; i++) {
            String in = getIntent().getStringExtra(Integer.toString(i));
            text.append(in);
            text.append("\r\n");
        }

        Thread t = new Thread(new myConn("ate"));
        t.start();
        //sendToServer("atand");
        //text.getText();

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

