package com.tmdb.android.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentHomeBinding
import com.tmdb.android.ui.adapter.GenreListAdapter
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

    private lateinit var genreListAdapter: GenreListAdapter
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()
        observeData()
        refresh()
        navigation()
    }

    private fun setupRecyclerView() {
        genreListAdapter = GenreListAdapter(viewModel, viewLifecycleOwner)
        movieListAdapter = MovieListAdapter(viewModel::onMovieClicked)

        binding.layoutHome.rvGenre.adapter = genreListAdapter
        binding.layoutHome.rvMovie.adapter = movieListAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                movieListAdapter.retry()
            }
        )
    }

    private fun observeData() {
        viewModel.setGenres()
        viewModel.getGenres.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    layoutNetworkState(true)
                    genreListAdapter.submitList(result.data)
                }
                is Resource.Error -> {
                    layoutNetworkState(false)
                }
            }
        }

        viewModel.getMovieByGenre.observe(viewLifecycleOwner) {
            viewModel.getTopRatedMovie.postValue(it)
        }

        viewModel.getTopRatedMovie.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(lifecycle, it)
        }
    }

    private fun layoutNetworkState(state: Boolean) {
        binding.layoutHome.root.isVisible = state
        binding.layoutErrorConnection.root.isVisible = !state
    }

    private fun refresh() {
        binding.apply {
            refresh.setOnRefreshListener {
                viewModel.setTopRatedMovie()
                observeData()
                requireActivity().window.decorView.clearFocus()
                refresh.isRefreshing = false
            }
        }
    }

    private fun navigation() {
        viewModel.navigateToDetail.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(HomeFragmentDirections.toMovieDetailFragment(it))
        })
        binding.layoutHome.edtSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toSearchFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}