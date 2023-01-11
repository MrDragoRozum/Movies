package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private List<Trailer> trailerList = new ArrayList<>();

    public void setTrailerList(List<Trailer>trailerList) {
        this.trailerList = trailerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trailer_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trailer trailer = trailerList.get(position);
        holder.textViewTrailer.setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewTrailer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTrailer = itemView.findViewById(R.id.textViewTrailer);
        }
    }
}
