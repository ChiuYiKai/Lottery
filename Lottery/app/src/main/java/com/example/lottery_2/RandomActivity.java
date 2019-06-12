package com.example.lottery_2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class RandomActivity extends AppCompatActivity {

    TextView rels;
    private ArrayList<ItemSet> itemSets;
    String[] aldata;
    int alsize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        rels = findViewById(R.id.result);
        Intent intent = this.getIntent();
        alsize = (intent.getIntExtra("all",0));
        aldata = new  String[alsize] ;
        for(int i =0;i<alsize;i++)
        {
            aldata[i] = intent.getStringExtra(Integer.toString(i));
        }
        randomanswer();
    }
    private void randomanswer()    {
        rels.setText(aldata[((int)(Math.random()*alsize))]);
        rels.setTextColor(Color.rgb((int)(Math.random()*200),(int)(Math.random()*200),(int)(Math.random()*200)));
    }
    public void Backto(View view) {
        RandomActivity.this.finish();
    }

    public void randomagain(View view) {
        randomanswer();
    }
}
