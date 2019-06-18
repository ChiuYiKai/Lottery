package com.example.muitiselect;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Activity acitvity;
    List<String> data;
    LayoutInflater inflater;

    public CustomAdapter(Activity acitvity) {
        this.acitvity = acitvity;
    }

    public CustomAdapter(Activity acitvity, List<String> data) {
        this.acitvity = acitvity;
        this.data = data;

        inflater = acitvity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;


        convertView = inflater.inflate(R.layout.list_view_item, parent, false);

        holder = new ViewHolder();

        holder.name = convertView.findViewById(R.id.name);
        holder.checkBox = convertView.findViewById(R.id.checkbox);
        holder.checkBox.setTag(position);

        convertView.setTag(holder);


        String name = data.get(position);

        holder.name.setText(name);

        if (MainActivity.isActionMode) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                int postion = (int) buttonView.getTag();

                if (MainActivity.UserSelection.contains(data.get(position))) {
                    MainActivity.UserSelection.remove(data.get(position));
                } else {
                    MainActivity.UserSelection.add(data.get(position));
                }

                MainActivity.actionMode.setTitle("已選擇： " + MainActivity.UserSelection.size());
            }
        });


        return convertView;
    }

    public void update(List<String> data) {
        this.data = data;

        notifyDataSetChanged();
    }

    public void removeItems(List<String> items) {
        for (String item : items) {
            data.remove(item);
        }

        notifyDataSetChanged();
    }

    public void AddItem(String item) {
        data.add(item);

        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView name;
        CheckBox checkBox;
    }
}
