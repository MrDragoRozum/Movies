package com.example.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.ReviewItemBinding
import com.example.movies.pojo.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    var reviewList: List<Review> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        private const val TYPE_POSITIVE = "Позитивный"
        private const val TYPE_NEUTRAL = "Нейтральный"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ReviewItemBinding.inflate(
            LayoutInflater
                .from(parent.context), parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviewList[position]
        val backgroundId = when (review.type) {
            TYPE_POSITIVE -> android.R.color.holo_green_light
            TYPE_NEUTRAL -> android.R.color.holo_orange_light
            else -> android.R.color.holo_red_light
        }
        val backgroundColor = ContextCompat.getColor(holder.itemView.context, backgroundId)
        with(holder.binding) {
            cardViewBackground.setCardBackgroundColor(backgroundColor)
            textViewReview.text = review.review
            textViewAuthor.text = review.author
        }
    }

    override fun getItemCount() = reviewList.size

    inner class ViewHolder(val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root)
}