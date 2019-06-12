package com.example.lottery_2;

import java.io.Serializable;
import java.util.ArrayList;

public class ItemSet implements Serializable {
    private String title;           // 集合名稱
    private ArrayList<Item> items;  // 集合

    public ItemSet(){
        title = "test";
        items = new ArrayList<>();
    }

    public ItemSet(String title){
        this.title = title;
        items = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public String getTitle(){
        return title;
    }

    public Item getItem(int index){
        return items.get(index);
    }

    public int getSize(){
        return items.size();
    }

    public void setTitle(String title){
        this.title = title;
    }

}
