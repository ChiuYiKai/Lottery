package com.example.muitiselect;

import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class RandomActivity extends AppCompatActivity {

    TextView rels1,rels2,rels3;

    ArrayList<String> aldata;
    int alsize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        rels1 = findViewById(R.id.result1);
        rels2 = findViewById(R.id.result2);
        rels3 = findViewById(R.id.result3);

        Intent intent = this.getIntent();

        aldata = intent.getStringArrayListExtra("dataKey");
        alsize = aldata.size();


        rels1.setText(aldata.get((int)(Math.random()*alsize)));
        rels1.setTextColor(Color.rgb((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));
        rels2.setText(aldata.get((int)(Math.random()*alsize)));
        rels2.setTextColor(Color.rgb((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));
        rels3.setText(aldata.get((int)(Math.random()*alsize)));
        rels3.setTextColor(Color.rgb((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));

        circlerandom();
    }
    private void circlerandom()
    {
        int timerdelay = 300;
        int adddelay = (int)(1000/(alsize/Math.sqrt(alsize)+1));
        for (int i = 0;i<alsize/Math.sqrt(alsize);i++)
        {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    rels1.setText(rels2.getText());
                    rels1.setTextColor(rels2.getTextColors());
                    rels2.setText(rels3.getText());
                    rels2.setTextColor(rels3.getTextColors());
                    rels3.setText(aldata.get((int)(Math.random()*alsize)));
                    rels3.setTextColor(Color.rgb((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));
                }
            },timerdelay);
            timerdelay +=adddelay;
        }
    }
    private void randomanswer()    {

        rels3.setText(aldata.get((int)(Math.random()*alsize)));
        rels3.setTextColor(Color.rgb((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));
    }
    public void Backto(View view) {
        RandomActivity.this.finish();
    }

    public void randomagain(View view) {
        circlerandom();
    }
}