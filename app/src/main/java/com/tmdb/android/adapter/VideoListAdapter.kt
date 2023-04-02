package com.tmdb.android.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.core.data.remote.response.VideoResult
import com.tmdb.android.core.utils.YOUTUBE_URL
import com.tmdb.android.core.utils.loadPhotoUrl
import com.tmdb.android.styling.databinding.ItemVideoBinding

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
                ivVideo.loadPhotoUrl(data.thumbnailPathUrl())
                root.setOnClickListener {
                    val url = Uri.parse(YOUTUBE_URL + data.key)
                    val intent = Intent(Intent.ACTION_VIEW, url)
                    itemView.context.startActivity(intent)
                }
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