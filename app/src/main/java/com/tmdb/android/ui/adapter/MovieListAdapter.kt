package com.tmdb.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.databinding.ItemMovieBinding
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.utils.loadPhotoUrl
import com.tmdb.android.utils.withDateFormat

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
                ivPoster.loadPhotoUrl(data.posterPathUrl())
                tvTitle.text = data.title
                tvReleaseDate.text = data.releaseDate.withDateFormat()
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