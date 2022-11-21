package com.tmdb.android.ui.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentSearchBinding
import com.tmdb.android.ui.adapter.LoadingStateAdapter
import com.tmdb.android.ui.adapter.MovieListAdapter
import com.tmdb.android.utils.EventObserver
import com.tmdb.android.utils.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setQuery(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        setupRecyclerView()
//        searchMovies()
        observeData()
        navigation()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        movieListAdapter = MovieListAdapter(viewModel::onMovieClicked)
        binding.rvMovie.adapter = movieListAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                movieListAdapter.retry()
            }
        )
    }

    private fun observeData() {
        viewModel.getSearchMovies.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(lifecycle, it)
        }

        viewModel.getQuery.observe(viewLifecycleOwner) { isEmpty ->
            if (isEmpty) {
                binding.searchView.requestFocus()
                binding.searchView.showKeyboard()
            }
        }
    }

    private fun searchMovies() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.setSearchMovies(query)
                requireActivity().window.decorView.clearFocus()
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {
                if (query != "") {
                    viewModel.setSearchMovies(query)
                    viewModel.setQuery(false)
                }
                return true
            }
        })
    }

    private fun navigation() {
        viewModel.navigateToDetail.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(SearchFragmentDirections.toMovieDetailFragment(it))
        })
    }
}