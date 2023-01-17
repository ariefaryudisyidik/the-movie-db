package com.tmdb.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.databinding.ItemGenreBinding
import com.tmdb.android.domain.model.Genre
import com.tmdb.android.ui.home.HomeViewModel
import com.tmdb.android.utils.activeButton
import com.tmdb.android.utils.inactiveButton

class GenreListAdapter(
    private val viewModel: HomeViewModel,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Genre, GenreListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Genre) {
            binding.apply {
                btnGenres.text = data.name
                btnGenres.setOnClickListener {
                    viewModel.setMovieByGenre(data.id)
                }


                viewModel.getGenreId.observe(lifecycleOwner) { genreId ->
                    if (genreId == data.id) {
                        itemView.context.activeButton(btnGenres)
                    } else {
                        itemView.context.inactiveButton(btnGenres)
                    }
                }
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