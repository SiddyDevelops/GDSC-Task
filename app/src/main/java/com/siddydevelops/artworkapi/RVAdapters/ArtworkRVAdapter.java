package com.siddydevelops.artworkapi.RVAdapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.siddydevelops.artworkapi.API.ArtworkItem;
import com.siddydevelops.artworkapi.ArtworkActivity;
import com.siddydevelops.artworkapi.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArtworkRVAdapter extends RecyclerView.Adapter<ArtworkRVAdapter.ArtworkViewholder> {

    private final List<ArtworkItem> artworkList;
    private static int i = 0;

    public ArtworkRVAdapter(List<ArtworkItem> artworkList) {
        this.artworkList = artworkList;
    }

    @NonNull
    @Override
    public ArtworkViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.artwork_layout_item_rv,parent,false);
        return new ArtworkViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtworkViewholder holder, int position) {
        holder.id.setText(String.valueOf(artworkList.get(position).getId()));
        holder.title.setText(artworkList.get(position).getTitle());
        holder.artist.setText(artworkList.get(position).getArtist_title());
        holder.yearOfOrigin.setText(artworkList.get(position).getDate_start());

        Set<String> set = new HashSet<String>();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ArtworkActivity.class);
                intent.putExtra("TITLE",artworkList.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("ARTIST",artworkList.get(holder.getAdapterPosition()).getArtist_title());
                intent.putExtra("DATE",artworkList.get(holder.getAdapterPosition()).getDate_start());
                intent.putExtra("PROVENANCE",artworkList.get(holder.getAdapterPosition()).getProvenance_text());
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isActivated())
                    view.setActivated(false);
                else
                    view.setActivated(true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return artworkList.size();
    }

    public class ArtworkViewholder extends RecyclerView.ViewHolder
    {
        TextView id;
        TextView title;
        TextView artist;
        TextView yearOfOrigin;
        CardView cardView;
        ImageView favIcon;
        public ArtworkViewholder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idTV);
            title = itemView.findViewById(R.id.titleTV);
            artist = itemView.findViewById(R.id.artistTV);
            yearOfOrigin = itemView.findViewById(R.id.yearOfOriginTV);
            cardView = itemView.findViewById(R.id.CardView);
            favIcon = itemView.findViewById(R.id.favIcon);
        }
    }
}
