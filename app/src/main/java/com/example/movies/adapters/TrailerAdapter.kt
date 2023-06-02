package com.example.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.databinding.TrailerItemBinding
import com.example.movies.pojo.Trailer

class TrailerAdapter : RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    var trailerList: List<Trailer> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onTrailerClickListener: OnTrailerClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = TrailerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trailer = trailerList[position]
        holder.binding.textViewTrailer.text = trailer.name
        holder.binding.textViewTrailer.setOnClickListener {
            onTrailerClickListener?.onTrailerClick(trailer)
        }
    }

    override fun getItemCount() = trailerList.size

    inner class ViewHolder(val binding: TrailerItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun interface OnTrailerClickListener {
        fun onTrailerClick(trailer: Trailer)
    }
}