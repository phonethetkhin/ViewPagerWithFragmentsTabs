package com.example.viewpagerwithfragmentstabs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.viewpagerwithfragmentstabs.Models.CategoryModel;
import com.example.viewpagerwithfragmentstabs.R;

import java.util.List;

public class SpnAdapter extends ArrayAdapter {
    List<CategoryModel> categoryModelList;

    public SpnAdapter(@NonNull Context context, int resource, int textViewResourceId, List<CategoryModel> categoryModelList) {
        super(context, resource, textViewResourceId);
        this.categoryModelList = categoryModelList;
    }

    @Override
    public int getCount() {
        return categoryModelList.size();
    }
    public View getCustomView(int i,View v,ViewGroup parent)
    {
        TextView tvShowCategoryName;
         v= LayoutInflater.from(parent.getContext()).inflate(R.layout.spinnerlistitem,parent,false);
         tvShowCategoryName=v.findViewById(R.id.tvShowCategoryName);
         tvShowCategoryName.setText(categoryModelList.get(i).getCategoryName());
         return v;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i,view,viewGroup);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position,convertView,parent);
    }
}
