package com.example.movies;

import android.util.Log;
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

    private final static String TYPE_POSITIVE = "Позитивный";
    private final static String TYPE_NEUTRAL = "Нейтральный";
    private final static String TAG = "ReviewAdapter";

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

        String type = review.getType();
        int backgroundId = android.R.color.holo_red_light;;
        if(type != null) {
            switch (type) {
                case TYPE_POSITIVE:
                    backgroundId = android.R.color.holo_green_light;
                    break;
                case TYPE_NEUTRAL:
                    backgroundId = android.R.color.holo_orange_light;
                    break;
            }
            int backgroundColor = ContextCompat
                    .getColor(holder.itemView.getContext(), backgroundId);
            holder.cardViewBackground.setCardBackgroundColor(backgroundColor);
            holder.textViewReview.setText(review.getReview());
            holder.textViewAuthor.setText(review.getAuthor());
        } else {
            String logError = holder.itemView.getContext().getString(R.string.error);
            String errorApology = holder.itemView.getContext().getString(R.string.error_apology);
            backgroundId = android.R.color.holo_red_dark;
            int backgroundColor = ContextCompat
                    .getColor(holder.itemView.getContext(), backgroundId);

            Log.e(TAG, logError + "\n" + errorApology);

            holder.cardViewBackground.setCardBackgroundColor(backgroundColor);
            holder.textViewAuthor.setText(logError);
            holder.textViewReview.setText(errorApology);
        }
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
