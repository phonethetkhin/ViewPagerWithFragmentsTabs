package com.example.viewpagerwithfragmentstabs.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerwithfragmentstabs.Database.MyDatabase;
import com.example.viewpagerwithfragmentstabs.Models.CategoryModel;
import com.example.viewpagerwithfragmentstabs.R;
import com.example.viewpagerwithfragmentstabs.UpdateCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.categorylist,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvCategoryName.setText(categoryModelList.get(position).getCategoryName());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(view.getContext(), UpdateCategory.class);
                Bundle b=new Bundle();
b.putParcelable("OldValue",categoryModelList.get(position));
            i.putExtras(b);
            view.getContext().startActivity(i);


            }
        });
holder.btnRemove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are You Sure You Want To Delete This !!!!!");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase mdb=new MyDatabase(view.getContext());
                mdb.RemoveCategory(categoryModelList.get(position).getCategoryID());
                categoryModelList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Remove Successfully", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();

    }
});
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvCategoryName;
        Button btnEdit,btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName=itemView.findViewById(R.id.tvShowCategoryName);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnRemove=itemView.findViewById(R.id.btnRemove);

        }
    }
}
