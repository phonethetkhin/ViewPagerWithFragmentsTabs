package com.example.viewpagerwithfragmentstabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.viewpagerwithfragmentstabs.Database.MyDatabase;
import com.example.viewpagerwithfragmentstabs.Models.CategoryModel;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateCategory extends AppCompatActivity {
TextInputEditText tietCategoryName;
Button btnSave;
MyDatabase mdb=new MyDatabase(UpdateCategory.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);
        tietCategoryName=findViewById(R.id.tietCategoryName);
        btnSave=findViewById(R.id.btnSave);
        getSupportActionBar().setTitle("Update Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final CategoryModel cmodel=getIntent().getParcelableExtra("OldValue");

        tietCategoryName.setText(cmodel.getCategoryName());
 btnSave.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         if(tietCategoryName.getText().toString().trim().length()<=0)
         {
             Toast.makeText(UpdateCategory.this, "Please Fill Updated Category Name", Toast.LENGTH_LONG).show();
         }
         else {
             mdb.UpdateCategory(cmodel.getCategoryID(), tietCategoryName.getText().toString());
             Toast.makeText(UpdateCategory.this, "Updated Successfully", Toast.LENGTH_LONG).show();
             finish();
         }
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
