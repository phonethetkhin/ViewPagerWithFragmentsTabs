package com.example.viewpagerwithfragmentstabs.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.viewpagerwithfragmentstabs.Adapters.CategoryAdapter;
import com.example.viewpagerwithfragmentstabs.Database.MyDatabase;
import com.example.viewpagerwithfragmentstabs.Models.CategoryModel;
import com.example.viewpagerwithfragmentstabs.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveCategoryFragment extends Fragment {
    TextInputEditText tietCategoryName;
    Button btnSave;
    RecyclerView rvShow;
    List<CategoryModel> categoryModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_save_category, container, false);

            tietCategoryName=v.findViewById(R.id.tietCategoryName);
            btnSave=v.findViewById(R.id.btnSave);
            rvShow=v.findViewById(R.id.rvShow);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(tietCategoryName.getText().toString().trim().length()<=0)
                    {
                        Toast.makeText(getContext(), "Pleas Fill Category Name", Toast.LENGTH_LONG).show();
                    }
                    else{
                    MyDatabase mdb=new MyDatabase(getContext());
                    if(mdb.InsertCategory(tietCategoryName.getText().toString()))
                    {
                        Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                        tietCategoryName.setText("");
                        onResume();
                    }
                    else{
                        Toast.makeText(getContext(), "Save Fail", Toast.LENGTH_LONG).show();
                    }
                }}
            });

onResume();


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyDatabase mdb=new MyDatabase(getContext());
        categoryModelList=mdb.GetCategory();
        rvShow.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        rvShow.setHasFixedSize(true);
        rvShow.setAdapter(new CategoryAdapter(categoryModelList));
    }
}
