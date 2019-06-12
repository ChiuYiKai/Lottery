package com.example.lottery_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

    private static final int RESULT_B = 1;

    private Intent intent;
    private Bundle bundle;
    private ItemSet itemSet;
    private EditText title;     // 集合名稱
    private EditText itemName;  // 選項名稱
    private EditText weight;    // 權重
    private ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        title = findViewById(R.id.Title);
        itemName = findViewById(R.id.ItemName);
        weight = findViewById(R.id.Weight);
        mlistView = findViewById(R.id.listview);


        intent = getIntent();
        bundle = new Bundle();
        itemSet = new ItemSet();
    }

    public void addItem(View view) {
        itemSet.addItem(new Item(itemName.getText().toString(),Integer.parseInt(weight.getText().toString())));
        refreshListView();
    }

    public void refreshListView(){

        // 宣告一個 ArrayList 來儲存 ItemSets 的 title
        ArrayList<String> data;
        data = new ArrayList<>();
        for(int i=0; i <itemSet.getSize(); i++){
            data.add(itemSet.getItem(i).getTitle() + " "+ itemSet.getItem(i).getWeight());
        }
        ListAdapter listadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        mlistView.setAdapter(listadapter);
    }


    // 結束 SecondActivity
    public void Leave(View view) {
        itemSet.setTitle(title.getText().toString());
        bundle.putSerializable("ItemSetKey", itemSet);
        intent.putExtras(bundle);
        setResult(RESULT_B, intent);
        this.finish();
    }
}
