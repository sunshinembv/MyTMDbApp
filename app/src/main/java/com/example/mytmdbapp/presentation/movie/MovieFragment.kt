package com.example.mytmdbapp.presentation.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mytmdbapp.R
import com.example.mytmdbapp.databinding.FragmentCinemaBinding
import com.example.mytmdbapp.presentation.adapter.CinemaAdapter
import com.example.mytmdbapp.utils.appComponent
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
        movieViewModel.getMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        popularAdapter = null
        topRatingsAdapter = null
        upcomingAdapter = null
    }

    private fun bindViewModel() {
        movieViewModel.popularMovies.observe(viewLifecycleOwner) {
            popularAdapter?.updateCinemaList(it)
        }

        movieViewModel.topRatingsMovies.observe(viewLifecycleOwner) {
            topRatingsAdapter?.updateCinemaList(it)
        }

        movieViewModel.upcomingMovies.observe(viewLifecycleOwner) {
            upcomingAdapter?.updateCinemaList(it)
        }

        movieViewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }
    }

    private fun openMovieDetailed(id: Int) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieDetailedFragment(
                id
            )
        )
    }

    private fun isLoading(isLoading: Boolean) {
        viewBinding.progressBar.isVisible = isLoading
        viewBinding.popularTextView.isVisible = !isLoading
        viewBinding.topRatingsTextView.isVisible = !isLoading
        viewBinding.upcomingTextView.isVisible = !isLoading
        viewBinding.moviesGroup.isVisible = !isLoading
    }
}