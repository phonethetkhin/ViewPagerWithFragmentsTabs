package com.example.viewpagerwithfragmentstabs.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.viewpagerwithfragmentstabs.Adapters.SpnAdapter;
import com.example.viewpagerwithfragmentstabs.Database.MyDatabase;
import com.example.viewpagerwithfragmentstabs.Models.CategoryModel;
import com.example.viewpagerwithfragmentstabs.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaveGameFragment extends Fragment {
    Spinner spnCategoryID;
    TextInputEditText tietGameName,tietDetail,tietPhoto,tietPrice;
    Button btnGoogle,btnSave,btnCancel;
    List<CategoryModel> categoryModelList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_save_game, container, false);
        spnCategoryID=v.findViewById(R.id.spnCategoryID);
        tietGameName=v.findViewById(R.id.tietGameName);
        tietDetail=v.findViewById(R.id.tietDetail);
        tietPhoto=v.findViewById(R.id.tietPhoto);
        tietPrice=v.findViewById(R.id.tietPrice);
        btnGoogle=v.findViewById(R.id.btnGoogle);
        btnSave=v.findViewById(R.id.btnSave);
        btnCancel=v.findViewById(R.id.btnCancel);
        //spinner
        MyDatabase mdb=new MyDatabase(getContext());
        categoryModelList=mdb.GetCategory();
        spnCategoryID.setAdapter(new SpnAdapter(getContext(),R.layout.spinnerlistitem,R.id.tvShowCategoryName,categoryModelList));
        //google
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                String url="https://www.google.com";
                i.setData(Uri.parse(url));
                view.getContext().startActivity(i);
            }
        });
        //save
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tietGameName.getText().toString().trim().length()<=0 || tietPhoto.getText().toString().trim().length()<=0 ||
                        tietDetail.getText().toString().trim().length()<=0 || tietPrice.getText().toString().trim().length()<=0)
                {
                    Toast.makeText(getContext(), "Please Fill All Information", Toast.LENGTH_LONG).show();
                }
                else
                {
                    MyDatabase mdb=new MyDatabase(getContext());
                    if(mdb.InsertGame(categoryModelList.get(spnCategoryID.getSelectedItemPosition()).getCategoryID(),
                            tietGameName.getText().toString(),
                            tietPhoto.getText().toString(),
                            tietDetail.getText().toString(),
                            Double.parseDouble(tietPrice.getText().toString())))
                    {
                        Toast.makeText(getContext(), "Save Successfully", Toast.LENGTH_LONG).show();
                        tietGameName.setText("");
                        tietPhoto.setText("");
                        tietDetail.setText("");
                        tietPrice.setText("");
                        tietGameName.requestFocus();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Save Fail", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
//cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tietGameName.setText("");
                tietPhoto.setText("");
                tietDetail.setText("");
                tietPrice.setText("");
                tietGameName.requestFocus();
            }
        });
        return v;
    }

}
