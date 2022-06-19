package com.example.mytmdbapp.presentation.tv_shows

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mytmdbapp.R
import com.example.mytmdbapp.databinding.FragmentCinemaBinding
import com.example.mytmdbapp.presentation.adapter.CinemaAdapter

class TvShowsFragment : Fragment(R.layout.fragment_cinema) {
    private val viewBinding by viewBinding(FragmentCinemaBinding::bind)
    private val tvShowsViewModel: TvShowsViewModel by viewModels()

    private var popularAdapter: CinemaAdapter? = null
    private var topRatingsAdapter: CinemaAdapter? = null
    private var upcomingAdapter: CinemaAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularAdapter = CinemaAdapter {
        }
        topRatingsAdapter = CinemaAdapter {
        }
        upcomingAdapter = CinemaAdapter {
        }

        with(viewBinding.popularRecyclerView) {
            adapter = popularAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }

        with(viewBinding.topRatingsRecyclerView) {
            adapter = topRatingsAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }

        with(viewBinding.upcomingRecyclerView) {
            adapter = upcomingAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }

        bindViewModel()
        tvShowsViewModel.getPopularMovies()
        tvShowsViewModel.getTopRatingsMovies()
        tvShowsViewModel.getUpcomingMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popularAdapter = null
        topRatingsAdapter = null
        upcomingAdapter = null
    }

    private fun bindViewModel() {
        tvShowsViewModel.popularTvShows.observe(viewLifecycleOwner) {
            popularAdapter?.updateCinemaList(it)
        }

        tvShowsViewModel.topRatingsTvShows.observe(viewLifecycleOwner) {
            topRatingsAdapter?.updateCinemaList(it)
        }

        tvShowsViewModel.upcomingTvShows.observe(viewLifecycleOwner) {
            upcomingAdapter?.updateCinemaList(it)
        }
    }
}
