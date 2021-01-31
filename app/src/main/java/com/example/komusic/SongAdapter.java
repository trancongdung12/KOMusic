package com.example.komusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.CategoryHolder> {

    private Context context;
    private  List<Song> mSongs;
    public SongAdapter(List<Song> mSongs, Context context) {
        this.mSongs = mSongs;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recentsong, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.img_view.setImageResource(mSongs.get(position).getImage());
        holder.tx_view.setText(mSongs.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playScreen = new Intent(context, Player.class);
                playScreen.putExtra("title",mSongs.get(position).getTitle());
                context.startActivity(playScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mSongs !=null){
            return mSongs.size();
        }
        return 0;
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        private ImageView img_view;
        private TextView tx_view;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            img_view = itemView.findViewById(R.id.img_category);
            tx_view = itemView.findViewById(R.id.title);


        }
    }
}
