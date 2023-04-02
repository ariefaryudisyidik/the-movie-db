package com.tmdb.android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.core.domain.model.Movie
import com.tmdb.android.core.utils.loadPhotoUrl
import com.tmdb.android.core.utils.withDateFormat
import com.tmdb.android.styling.databinding.ItemMovieBinding

class MovieListAdapter(private var onDetailClick: (Movie) -> Unit) :
    PagingDataAdapter<Movie, MovieListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            binding.apply {
                tvReleaseDate.text = data.releaseDate.withDateFormat()
                ivPoster.loadPhotoUrl(data.posterPathUrl())
                tvTitle.text = data.title
                tvAverageRating.text = data.voteAverage.toString()
                tvReadMore.setOnClickListener { onDetailClick(data) }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }
}