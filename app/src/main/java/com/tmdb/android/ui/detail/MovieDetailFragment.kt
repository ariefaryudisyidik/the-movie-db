package com.tmdb.android.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentMovieDetailBinding
import com.tmdb.android.domain.model.Movie
import com.tmdb.android.utils.loadPhotoUrl
import com.tmdb.android.utils.withDateFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)

        showDetails(args.movie)
    }

    private fun showDetails(data: Movie) {
        binding.apply {
            ivBackdrop.loadPhotoUrl(data.backdropPathUrl())
            ivPoster.loadPhotoUrl(data.posterPathUrl())
            tvTitle.text = data.title
            tvOverview.text = data.overview
            tvReleaseDate.text = data.releaseDate.withDateFormat()
            tvAverageRating.text = data.voteAverage.toString()
            tvRateCount.text = data.voteCount.toString()
            tvPopularity.text = data.popularity.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}