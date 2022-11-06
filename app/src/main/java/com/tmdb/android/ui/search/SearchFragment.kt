package com.tmdb.android.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        Log.d(TAG, "1 onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "2 onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        setupRecyclerView()
//        searchMovies()
        observeData()
        navigation()
        Log.d(TAG, "3 onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        searchMovies()
        Log.d(TAG, "4 onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "5 onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "6 onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "7 onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "8 onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "9 onSaveInstanceState")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d(TAG, "10 onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "11 onDestroy")
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

    companion object {
        const val TAG = "SearchFragment"
    }
}