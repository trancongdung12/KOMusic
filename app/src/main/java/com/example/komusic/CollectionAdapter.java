package com.example.komusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CategoryHolder> {

    private Context context;
    private  List<Song> mSongs;
    public CollectionAdapter(List<Song> mSongs, Context context) {
        this.mSongs = mSongs;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        holder.img_view.setImageResource(mSongs.get(position).getImage());
        holder.txt_title.setText(mSongs.get(position).getTitle());
        holder.txt_artist.setText(mSongs.get(position).getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playScreen = new Intent(context, Player.class);
                playScreen.putExtra("id",String.valueOf(mSongs.get(position).getId()));
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
        private TextView txt_title;
        private TextView txt_artist;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            img_view = itemView.findViewById(R.id.img_song);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_artist = itemView.findViewById(R.id.txt_artist);




        }
    }
}
