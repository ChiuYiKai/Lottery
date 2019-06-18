package com.example.muitiselect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static boolean isActionMode = false;
    public static List<String> UserSelection = new ArrayList<>();
    public static ActionMode actionMode = null;
    public ArrayList<String> data;
    public CustomAdapter adapter;
    public EditText input;

    private SharedPreferences savedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.Listview);

        input = findViewById(R.id.editText);

        listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(modeListener);

        Gson gson = new Gson();

        savedData = getSharedPreferences("savedData",0);
        String savedJson = savedData.getString("savedJson","");

        data = gson.fromJson(savedJson, new TypeToken<List<String>>(){}.getType());

        if(data == null) {
            data = new ArrayList<>();
            data.add("!!!!");
        }

        adapter = new CustomAdapter(this, data);
        listView.setAdapter(adapter);

    }

    AbsListView.MultiChoiceModeListener modeListener = new AbsListView.MultiChoiceModeListener() {
        @Override
        public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.action_mode_menu, menu);

            mode.setTitle("選擇項目");

            isActionMode = true;

            actionMode = mode;

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()){

                case R.id.action_delete:
                    adapter.removeItems(UserSelection);
                    mode.finish();
                    return true;


                default:
                    return false;
            }


        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isActionMode = false;

            actionMode = null;
            UserSelection.clear();
        }
    };


    public void onAddButtonClick(View view) {
        String item = input.getText().toString();

        adapter.AddItem(item);
    }

    public void onStartButtonClick(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, RandomActivity.class);

        intent.putStringArrayListExtra("dataKey", data);

        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Gson gson = new Gson();
        String json = gson.toJson(data);

        savedData = this.getApplicationContext().getSharedPreferences("savedData",MODE_PRIVATE);
        savedData.edit().putString("savedJson",json).apply();
    }
}
