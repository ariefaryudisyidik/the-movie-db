package com.tmdb.android.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.tmdb.android.R
import com.tmdb.android.adapter.ReviewListAdapter
import com.tmdb.android.adapter.VideoListAdapter
import com.tmdb.android.core.domain.model.Movie
import com.tmdb.android.core.utils.EventObserver
import com.tmdb.android.core.utils.Resource
import com.tmdb.android.core.utils.loadPhotoUrl
import com.tmdb.android.core.utils.withDateFormat
import com.tmdb.android.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()

    private val args by navArgs<MovieDetailFragmentArgs>()
    private lateinit var videoListAdapter: VideoListAdapter
    private lateinit var reviewListAdapter: ReviewListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)

        setupRecyclerView()
        setupChipGroup()
        observeData(args.movie)
    }

    private fun setupChipGroup() {

    }

    private fun setupRecyclerView() {
        videoListAdapter = VideoListAdapter()
        binding.layoutVideo.rvVideo.adapter = videoListAdapter

        reviewListAdapter = ReviewListAdapter()
        binding.layoutReview.rvReview.adapter = reviewListAdapter
    }

    private fun showDetails(data: Movie) {
        binding.apply {
            ivBackdrop.loadPhotoUrl(data.backdropPathUrl())
            ivPoster.loadPhotoUrl(data.posterPathUrl())
            tvTitle.text = data.title
        }

        binding.layoutDetail.apply {
            root.isVisible = true
            tvOverview.text = data.overview
            tvReleaseDate.text = data.releaseDate.withDateFormat()
            tvAverageRating.text = data.voteAverage.toString()
            tvRateCount.text = data.voteCount.toString()
            tvPopularity.text = data.popularity.toString()
        }
    }

    private fun observeData(data: Movie) {
        viewModel.setVideos(data.id)
        viewModel.getVideos.observe(viewLifecycleOwner, EventObserver { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (result.data?.videos?.results?.isNotEmpty() == true) {
                        binding.layoutVideo.root.isVisible = true
                        videoListAdapter.submitList(result.data!!.videos.results)
                    }

                    result.data?.genres?.map {
                        val chip = Chip(requireContext()).apply {
                            text = it.name
                            setChipBackgroundColorResource(com.tmdb.android.styling.R.color.black_2)
                            setTextAppearanceResource(com.tmdb.android.styling.R.style.ChipTextStyle)
                        }
                        binding.layoutGenre.chipGroup.addView(chip)
                    }

                    showDetails(data)
                }
                is Resource.Error -> {}
            }
        })

        viewModel.setReviews(data.id)
        viewModel.getReviews.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (result.data?.isNotEmpty() == true) {
                        binding.layoutReview.root.isVisible = true
                        reviewListAdapter.submitList(result.data)
                    }
                }
                is Resource.Error -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}