package com.example.viewpagerwithfragmentstabs.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.viewpagerwithfragmentstabs.Adapters.GameAdapter;
import com.example.viewpagerwithfragmentstabs.Adapters.SpnAdapter;
import com.example.viewpagerwithfragmentstabs.Database.MyDatabase;
import com.example.viewpagerwithfragmentstabs.Models.CategoryModel;
import com.example.viewpagerwithfragmentstabs.Models.GameModel;
import com.example.viewpagerwithfragmentstabs.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends Fragment {

Spinner spnCategoryID;
RecyclerView rvShowGame;
List<CategoryModel> categoryModelList;
List<GameModel> gameModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_game_list, container, false);

        spnCategoryID=v.findViewById(R.id.spnCategoryID);
        rvShowGame=v.findViewById(R.id.rvShowGame);
        //spinner
        MyDatabase mdb=new MyDatabase(getContext());

        categoryModelList=mdb.GetCategory();
        spnCategoryID.setAdapter(new SpnAdapter(getContext(),R.layout.spinnerlistitem,R.id.tvShowCategoryName,categoryModelList));

        //rv
        spnCategoryID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                MyDatabase mdb=new MyDatabase(getContext());

                gameModelList=mdb.getAllGames(categoryModelList.get(spnCategoryID.getSelectedItemPosition()).getCategoryID());

                rvShowGame.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
                rvShowGame.setHasFixedSize(true);
                rvShowGame.setAdapter(new GameAdapter(gameModelList));            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        return v;
    }


}
