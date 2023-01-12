package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<Review> reviewList = new ArrayList<>();

    private final static String POSITIVE = "Позитивный";

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        //Из-за него я потратил час
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Review review = reviewList.get(position);

        holder.textViewAuthor.setText(review.getAuthor());
        int backgroundId;
        if(review.getType().equals(POSITIVE)) {
            backgroundId = android.R.color.holo_green_light;
        } else {
            backgroundId = android.R.color.holo_red_light;
        }
        int backgroundColor = ContextCompat.getColor(holder.itemView.getContext(), backgroundId);
        holder.cardViewBackground.setCardBackgroundColor(backgroundColor);
        holder.textViewReview.setText(review.getReview());

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewAuthor;
        private final TextView textViewReview;
        private final CardView cardViewBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewReview = itemView.findViewById(R.id.textViewReview);
            cardViewBackground = itemView.findViewById(R.id.cardViewBackground);
        }
    }
}
