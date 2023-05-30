package com.example.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.MovieItemBinding
import com.example.movies.pojo.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList: List<Movie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onReachEndListener: OnReachEndListener? = null
    var onOpenScreenDetailListener: OnOpenScreenDetailListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = MovieItemBinding.inflate(
            LayoutInflater
                .from(parent.context), parent, false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        with(holder) {
            with(binding) {
                val movie = movieList[position]
                Glide.with(itemView)
                    .load(movie.poster.url)
                    .into(imageViewPoster)

                val rating = movie.rating.kp
                val backgroundId = when {
                    rating > 7 -> R.drawable.circle_green
                    rating > 5 -> R.drawable.circle_orange
                    else -> R.drawable.circle_red
                }
                val background = ContextCompat.getDrawable(itemView.context, backgroundId)
                textViewRating.background = background
                textViewRating.text = rating.toString().substring(0, 3)

                if (movieList.size - 10 <= position) onReachEndListener?.onReachEnd()
                imageViewPoster.setOnClickListener {
                    onOpenScreenDetailListener?.onOpenScreenDetail(movie)
                }
            }

        }
    }

    override fun getItemCount() = movieList.size

    interface OnReachEndListener {
        fun onReachEnd()
    }

    interface OnOpenScreenDetailListener {
        fun onOpenScreenDetail(movie: Movie)
    }

    inner class MovieViewHolder(val binding: MovieItemBinding) : ViewHolder(binding.root)
}