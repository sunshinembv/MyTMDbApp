package com.example.mytmdbapp.presentation.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mytmdbapp.R
import com.example.mytmdbapp.databinding.FragmentCinemaBinding
import com.example.mytmdbapp.presentation.UIState
import com.example.mytmdbapp.presentation.adapter.CinemaAdapter
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDataUI
import com.example.mytmdbapp.utils.appComponent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : Fragment(R.layout.fragment_cinema) {

    private val viewBinding by viewBinding(FragmentCinemaBinding::bind)

    @Inject
    lateinit var movieViewModelFactory: MovieViewModel.Factory

    private val movieViewModel: MovieViewModel by viewModels {
        movieViewModelFactory
    }

    private var popularAdapter: CinemaAdapter? = null
    private var topRatingsAdapter: CinemaAdapter? = null
    private var upcomingAdapter: CinemaAdapter? = null

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularAdapter = CinemaAdapter { id ->
            openMovieDetailed(id)
        }
        topRatingsAdapter = CinemaAdapter { id ->
            openMovieDetailed(id)
        }
        upcomingAdapter = CinemaAdapter { id ->
            openMovieDetailed(id)
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popularAdapter = null
        topRatingsAdapter = null
        upcomingAdapter = null
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            movieViewModel.movies.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is UIState.Success<MovieDataUI> -> {
                            viewVisibility(uiState)
                            popularAdapter?.updateCinemaList(uiState.dataUI.moviesPopular)
                            topRatingsAdapter?.updateCinemaList(uiState.dataUI.moviesTopRatings)
                            upcomingAdapter?.updateCinemaList(uiState.dataUI.moviesUpcoming)
                        }
                        is UIState.Error -> {
                            viewVisibility(uiState)
                            Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_LONG)
                                .show()
                        }
                        is UIState.IsLoading -> viewVisibility(uiState)
                    }
                }
        }
    }

    private fun openMovieDetailed(id: Int) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieDetailedFragment(
                id
            )
        )
    }

    private fun viewVisibility(uiState: UIState<MovieDataUI>) {
        Timber.d(uiState.toString())
        viewBinding.progressBar.isVisible = uiState is UIState.IsLoading
        viewBinding.popularTextView.isVisible = uiState is UIState.Success
        viewBinding.topRatingsTextView.isVisible = uiState is UIState.Success
        viewBinding.upcomingTextView.isVisible = uiState is UIState.Success
        viewBinding.moviesGroup.isVisible = uiState is UIState.Success
    }
}