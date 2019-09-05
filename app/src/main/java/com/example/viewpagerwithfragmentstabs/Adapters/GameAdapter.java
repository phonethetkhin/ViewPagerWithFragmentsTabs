package com.example.viewpagerwithfragmentstabs.Adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.viewpagerwithfragmentstabs.Database.MyDatabase;
import com.example.viewpagerwithfragmentstabs.Models.GameModel;
import com.example.viewpagerwithfragmentstabs.R;
import com.example.viewpagerwithfragmentstabs.UpdateGame;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    List<GameModel> gameModelList;

    public GameAdapter(List<GameModel> gameModelList) {
        this.gameModelList = gameModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.gamelistitem,parent,false);
        ViewHolder holder=new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Picasso.get().load(gameModelList.get(position).getPhoto()).into(holder.imgPhoto);
holder.tvGameName.setText(gameModelList.get(position).getGameName());
holder.tvPrice.setText("$ "+gameModelList.get(position).getPrice()+" USD");
holder.btnEdit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(view.getContext(), UpdateGame.class);
        Bundle b=new Bundle();
        b.putParcelable("OldValue",gameModelList.get(position));
        i.putExtras(b);
        view.getContext().startActivity(i);
    }
});
holder.btnRemove.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are You Sure You Want To Delete Thi s??????");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabase mdb=new MyDatabase(view.getContext());
                mdb.RemoveGame(gameModelList.get(position).getGameID());
                gameModelList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
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
        return gameModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvGameName,tvPrice;
        Button btnEdit,btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto=itemView.findViewById(R.id.imgPhoto);
            tvGameName=itemView.findViewById(R.id.tvGameName);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnRemove=itemView.findViewById(R.id.btnRemove);
        }
    }
}
