package com.tmdb.android.ui.home

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentHomeBinding
import com.tmdb.android.ui.adapter.CategoryListAdapter
import com.tmdb.android.ui.adapter.LoadingStateAdapter
import com.tmdb.android.ui.adapter.MovieListAdapter
import com.tmdb.android.utils.EventObserver
import com.tmdb.android.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var categoryListAdapter: CategoryListAdapter
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()
        observeData()
        searchMovies()
        navigation()
    }

    private fun setupRecyclerView() {
        categoryListAdapter = CategoryListAdapter()
        movieListAdapter = MovieListAdapter(viewModel::onMovieClicked)

        binding.rvCategory.adapter = categoryListAdapter
        binding.rvMovie.adapter = movieListAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                movieListAdapter.retry()
            }
        )
    }

    private fun observeData() {
        viewModel.genres.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    categoryListAdapter.submitList(result.data)
                }
                else -> {}
            }
        }

        viewModel.movies.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(lifecycle, it)
        }

        viewModel.topRatedMovies.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(lifecycle, it)
        }
    }

    private fun searchMovies() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchMovies(query)
                requireActivity().window.decorView.clearFocus()
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                viewModel.searchMovies(query)
                return true
            }
        })
    }

    private fun navigation() {
        viewModel.navigateToDetail.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.toMovieDetailFragment(it))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}