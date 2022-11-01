package com.tmdb.android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentHomeBinding
import com.tmdb.android.ui.adapter.LoadingStateAdapter
import com.tmdb.android.ui.adapter.MovieListAdapter
import com.tmdb.android.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()
        searchMovies()
        getTopRatedMovie()
        navigateToDetail()
    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(viewModel::onMovieClicked)
        binding.rvMovie.adapter = movieListAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                movieListAdapter.retry()
            }
        )
    }

    private fun searchMovies() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                requireActivity().window.decorView.clearFocus()
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.searchMovies(query)
                return true
            }
        })

        viewModel.movies.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(lifecycle, it)
        }

    }

    private fun getTopRatedMovie() {
        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(lifecycle, it)
        }
    }

    private fun navigateToDetail() {
        viewModel.navigateToDetail.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.toMovieDetailFragment(it))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}