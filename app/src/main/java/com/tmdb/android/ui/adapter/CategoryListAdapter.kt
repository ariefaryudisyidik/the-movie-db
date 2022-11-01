package com.tmdb.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.databinding.ItemCategoriesBinding
import com.tmdb.android.domain.model.Genre

class CategoryListAdapter : ListAdapter<Genre, CategoryListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCategoriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Genre) {
            binding.btnCategories.text = data.name
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