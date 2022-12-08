package com.example.moneymanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class CategoryAdapter extends BaseAdapter {
    private Fragment fragment;
    private String items[];

    public CategoryAdapter(Fragment fragment, String[] items) {
        this.fragment = fragment;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = fragment.getLayoutInflater();
        view = inflater.inflate(R.layout.category_item_name, null);
        Button button = (Button) view.findViewById(R.id.button_category_item);
        button.setText(items[i]);
        return view;
    }
}
