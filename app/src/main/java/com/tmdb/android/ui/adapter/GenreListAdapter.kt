package com.tmdb.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.databinding.ItemGenresBinding
import com.tmdb.android.domain.model.Genre
import com.tmdb.android.ui.home.HomeViewModel

class GenreListAdapter(
    private val viewModel: HomeViewModel
) : ListAdapter<Genre, GenreListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Genre) {
            binding.btnGenres.text = data.name
            binding.btnGenres.setOnClickListener {
                viewModel.getMovieByGenre(data.id)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Genre>() {
            override fun areItemsTheSame(oldItem: Genre, newItem: Genre) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Genre, newItem: Genre) =
                oldItem == newItem
        }
    }
}