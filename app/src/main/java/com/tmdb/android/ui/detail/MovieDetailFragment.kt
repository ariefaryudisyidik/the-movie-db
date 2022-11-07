package com.tmdb.android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentMovieDetailBinding
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.ui.adapter.GenreDetailAdapter
import com.tmdb.android.ui.adapter.VideoListAdapter
import com.tmdb.android.utils.Resource
import com.tmdb.android.utils.loadPhotoUrl
import com.tmdb.android.utils.withDateFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()

    private val args by navArgs<MovieDetailFragmentArgs>()
    private lateinit var genreDetailAdapter: GenreDetailAdapter
    private lateinit var videoListAdapter: VideoListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)

        setupRecyclerView()
        observeData(args.movie)
    }

    private fun setupRecyclerView() {
        genreDetailAdapter = GenreDetailAdapter()
        binding.layoutGenre.rvGenre.adapter = genreDetailAdapter

        videoListAdapter = VideoListAdapter()
        binding.layoutVideo.rvVideo.adapter = videoListAdapter
    }

    private fun showDetails(data: Movie) {
        binding.apply {
            ivBackdrop.loadPhotoUrl(data.backdropPathUrl())
            ivPoster.loadPhotoUrl(data.posterPathUrl())
            tvTitle.text = data.title
            layoutDetail.root.isVisible = true
            layoutVideo.root.isVisible = true
            layoutReview.root.isVisible = true
            layoutDetail.tvOverview.text = data.overview
            layoutDetail.tvReleaseDate.text = data.releaseDate.withDateFormat()
            layoutDetail.tvAverageRating.text = data.voteAverage.toString()
            layoutDetail.tvRateCount.text = data.voteCount.toString()
            layoutDetail.tvPopularity.text = data.popularity.toString()
        }
    }

    private fun observeData(data: Movie) {
        viewModel.setVideos(data.id)
        viewModel.getVideos.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    genreDetailAdapter.submitList(result.data?.genres)
                    videoListAdapter.submitList(result.data?.videos?.results)
                    showDetails(data)
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