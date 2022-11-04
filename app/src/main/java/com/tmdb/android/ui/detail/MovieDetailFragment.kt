package com.tmdb.android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentMovieDetailBinding
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.ui.adapter.GenreListAdapter
import com.tmdb.android.utils.loadPhotoUrl
import com.tmdb.android.utils.withDateFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModels()

    private val args by navArgs<MovieDetailFragmentArgs>()
    private lateinit var genreListAdapter: GenreListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)

        setupRecyclerView()
        showDetails(args.movie)
        observeData()
    }

    private fun setupRecyclerView() {
        genreListAdapter = GenreListAdapter(viewModel, viewLifecycleOwner)
    }

    private fun showDetails(data: Movie) {
        viewModel.setVideos(data.id)
        binding.apply {
            ivBackdrop.loadPhotoUrl(data.backdropPathUrl())
            ivPoster.loadPhotoUrl(data.posterPathUrl())
            tvTitle.text = data.title
            layoutDetail.tvOverview.text = data.overview
            layoutDetail.tvReleaseDate.text = data.releaseDate.withDateFormat()
            layoutDetail.tvAverageRating.text = data.voteAverage.toString()
            layoutDetail.tvRateCount.text = data.voteCount.toString()
            layoutDetail.tvPopularity.text = data.popularity.toString()
        }
    }

    private fun observeData() {
        viewModel.getVideos.observe(viewLifecycleOwner) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}