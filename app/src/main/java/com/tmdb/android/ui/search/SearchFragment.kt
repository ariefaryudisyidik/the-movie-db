package com.tmdb.android.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.tmdb.android.R
import com.tmdb.android.databinding.FragmentSearchBinding
import com.tmdb.android.utils.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)
        binding.searchView.showKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}