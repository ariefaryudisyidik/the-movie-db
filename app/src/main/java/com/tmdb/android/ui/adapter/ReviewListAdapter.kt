package com.tmdb.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tmdb.android.databinding.ItemReviewBinding
import com.tmdb.android.domain.model.Review
import com.tmdb.android.utils.IMAGE_URL
import com.tmdb.android.utils.loadPhotoUrl

class ReviewListAdapter : ListAdapter<Review, ReviewListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Review) {
            binding.apply {
                val avatarPath = data.authorDetails.avatarPath?.replace("/https", "https")
                val avatarPath2 = IMAGE_URL + data.authorDetails.avatarPath
                ivProfile.loadPhotoUrl(avatarPath2)
                tvName.text = data.authorDetails.username
                tvContent.text = data.content
                tvRating.text = data.authorDetails.rating.toString()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Review, newItem: Review) =
                oldItem == newItem
        }
    }
}