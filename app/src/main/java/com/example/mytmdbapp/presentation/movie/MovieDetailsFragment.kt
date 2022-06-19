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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.mytmdbapp.R
import com.example.mytmdbapp.databinding.FragmentMovieDetailedBinding
import com.example.mytmdbapp.presentation.UIState
import com.example.mytmdbapp.presentation.adapter.CinemaAdapter
import com.example.mytmdbapp.presentation.movie.adapter.CreditsAdapter
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDetailsUI
import com.example.mytmdbapp.utils.appComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_detailed) {

    private val viewBinding by viewBinding(FragmentMovieDetailedBinding::bind)
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        movieDetailsFactory
    }
    private var creditsAdapter: CreditsAdapter? = null
    private var cinemaAdapter: CinemaAdapter? = null

    @Inject
    lateinit var movieDetailsFactory: MovieDetailsViewModel.Factory

    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditsAdapter = CreditsAdapter()
        cinemaAdapter = CinemaAdapter {}

        with(viewBinding.castRecyclerView) {
            adapter = creditsAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }

        with(viewBinding.recommendationsRecyclerView) {
            adapter = cinemaAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.HORIZONTAL
            }
        }
        bindViewModel()
        movieDetailsViewModel.getMovieDetailedById(args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        creditsAdapter = null
        cinemaAdapter = null
    }

    private fun bindViewModel() {
        lifecycleScope.launch {
            movieDetailsViewModel.movieDetails.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { uiState ->
                    when (uiState) {
                        is UIState.Success<MovieDetailsUI> -> {
                            setData(uiState.dataUI)
                            creditsAdapter?.updateCreditsList(uiState.dataUI.credits)
                            cinemaAdapter?.updateCinemaList(uiState.dataUI.recommendations)
                            viewVisibility(uiState)
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

    private fun setData(movieDetailsUI: MovieDetailsUI) {
        with(viewBinding) {
            movieTitle.text = movieDetailsUI.title
            movieGenre.text = movieDetailsUI.genre
                .toString()
                .replace("[", "")
                .replace("]", "")
            movieOverview.text = movieDetailsUI.overView
            creditsAdapter?.updateCreditsList(movieDetailsUI.credits)
            cinemaAdapter?.updateCinemaList(movieDetailsUI.recommendations)

            Glide.with(requireContext())
                .load("${getString(R.string.image_url)}${movieDetailsUI.bannerPath}")
                .placeholder(R.drawable.ic_downloading_24).error(R.drawable.ic_error_download_24)
                .into(movieBackdrop)
            Glide.with(requireContext())
                .load("${getString(R.string.image_url)}${movieDetailsUI.posterPath}")
                .placeholder(R.drawable.ic_downloading_24).error(R.drawable.ic_error_download_24)
                .into(movieIcon)
        }
    }

    private fun viewVisibility(uiState: UIState<MovieDetailsUI>) {
        viewBinding.progressBar.isVisible = uiState is UIState.IsLoading
        viewBinding.movieDetailedGroup.isVisible = uiState is UIState.Success
    }
}
