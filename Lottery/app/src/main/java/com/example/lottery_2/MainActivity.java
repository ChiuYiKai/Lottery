package com.example.lottery_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_FROM_A = 0;
    private static final int RESULT_B = 1;

    private ListView mlistView;
    private ListAdapter mlistadapter;
    private ArrayList<ItemSet> itemSets;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle = new Bundle();
        itemSets = new ArrayList<>();

        // 把 titles 丟到 ListView 顯示
        mlistView = findViewById(R.id.listview);
        refreshListView();
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RandomActivity.class);
                bundle.putSerializable("ItemSetKey", itemSets);
                int count =0,allwi = 0;
                for (int i =0;i<itemSets.get(position).getSize();i++)
                {
                    allwi += itemSets.get(position).getItem(i).getWeight();
                }
                intent.putExtra("all",allwi);
                for (int i =0;i<itemSets.get(position).getSize();i++)
                {
                    for(int j =0;j<itemSets.get(position).getItem(i).getWeight();j++)
                    {
                        intent.putExtra(Integer.toString(count),itemSets.get(position).getItem(i).getTitle());
                        count++;
                    }
                }
                //intent.putExtra("123","123");
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_FROM_A);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar Item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshListView(){

        // 宣告一個 ArrayList 來儲存 ItemSets 的 title
        ArrayList<String> titles;
        titles = new ArrayList<>();
        for(int i=0; i <itemSets.size(); i++){
            titles.add(itemSets.get(i).getTitle());
        }
        mlistadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        mlistView.setAdapter(mlistadapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_B && requestCode==REQUEST_FROM_A){
            if (data != null) {
                itemSets.add((ItemSet) Objects.requireNonNull(data.getExtras()).getSerializable("ItemSetKey"));
                refreshListView();
            }
        }
    }
}