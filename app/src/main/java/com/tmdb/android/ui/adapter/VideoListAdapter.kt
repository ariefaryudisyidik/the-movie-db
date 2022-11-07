package com.tmdb.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.data.remote.response.VideoResult
import com.tmdb.android.databinding.ItemVideoBinding

class VideoListAdapter : ListAdapter<VideoResult, VideoListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VideoResult) {
            binding.apply {
                tvTitle.text = data.name
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<VideoResult>() {
            override fun areItemsTheSame(oldItem: VideoResult, newItem: VideoResult) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: VideoResult, newItem: VideoResult) =
                oldItem == newItem
        }
    }
}