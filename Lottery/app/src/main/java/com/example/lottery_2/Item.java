package com.example.lottery_2;

import java.io.Serializable;

public class Item implements Serializable {
    private String title;  // 名稱
    private int weight;    // 權重

    public Item(){
        title = "N/A";
        weight = 1;
    }

    public Item(String title, int weight){
        this.title = title;
        this.weight = weight;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setweight(int weight){
        this.weight = weight;
    }

    public String getTitle(){
        return title;
    }

    public int getWeight(){
        return weight;
    }
}


