package com.example.viewpagerwithfragmentstabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.viewpagerwithfragmentstabs.Adapters.SpnAdapter;
import com.example.viewpagerwithfragmentstabs.Database.MyDatabase;
import com.example.viewpagerwithfragmentstabs.Models.CategoryModel;
import com.example.viewpagerwithfragmentstabs.Models.GameModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UpdateGame extends AppCompatActivity {
    Spinner spnCategoryID;
    TextInputEditText tietGameName,tietPhoto,tietDetail,tietPrice;
    Button btnUpdate,btnCancel,btnGoogle;
List<CategoryModel> categoryModelList;
MyDatabase mdb=new MyDatabase(UpdateGame.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_game);
        spnCategoryID=findViewById(R.id.spnCategoryID);
        tietGameName=findViewById(R.id.tietGameName);
        tietPhoto=findViewById(R.id.tietPhoto);
        tietDetail=findViewById(R.id.tietDetail);
        tietPrice=findViewById(R.id.tietPrice);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);
        btnGoogle=findViewById(R.id.btnGoogle);
        getSupportActionBar().setTitle("Update Game");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setting old value
        final GameModel gmodel=getIntent().getParcelableExtra("OldValue");
        tietGameName.setText(gmodel.getGameName());
        tietPhoto.setText(gmodel.getPhoto());
        tietDetail.setText(gmodel.getDetail());
        tietPrice.setText(gmodel.getPrice()+"");
        //spinner
        categoryModelList=mdb.GetCategory();
        spnCategoryID.setAdapter(new SpnAdapter(UpdateGame.this,R.layout.spinnerlistitem,R.id.tvShowCategoryName,categoryModelList));
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
        //update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tietGameName.getText().toString().trim().length()<=0||
                tietPhoto.getText().toString().trim().length()<=0||
                tietDetail.getText().toString().trim().length()<=0||
                tietPrice.getText().toString().trim().length()<=0)
                {
                    Toast.makeText(UpdateGame.this, "Please Fill All Information", Toast.LENGTH_LONG).show();
                }
                else
                {
                    mdb.UpdateGame(gmodel.getGameID(),categoryModelList.get(spnCategoryID.getSelectedItemPosition()).getCategoryID(),
                            tietGameName.getText().toString(),
                            tietPhoto.getText().toString(),
                            tietDetail.getText().toString(),
                            Double.parseDouble(tietPrice.getText().toString()));
                    Toast.makeText(UpdateGame.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
