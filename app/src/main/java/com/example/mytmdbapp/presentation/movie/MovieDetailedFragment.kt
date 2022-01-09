package com.example.mytmdbapp.presentation.movie

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.mytmdbapp.R
import com.example.mytmdbapp.databinding.FragmentMovieDetailedBinding
import com.example.mytmdbapp.presentation.adapter.CinemaAdapter
import com.example.mytmdbapp.presentation.movie.adapter.CreditsAdapter
import com.example.mytmdbapp.presentation.movie.ui_model.MovieDetailedUIModel
import com.example.mytmdbapp.utils.appComponent
import javax.inject.Inject

class MovieDetailedFragment : Fragment(R.layout.fragment_movie_detailed) {

    private val viewBinding by viewBinding(FragmentMovieDetailedBinding::bind)
    private val movieDetailedViewModel: MovieDetailedViewModel by viewModels {
        movieDetailedFactory
    }
    private var creditsAdapter: CreditsAdapter? = null
    private var cinemaAdapter: CinemaAdapter? = null

    @Inject
    lateinit var movieDetailedFactory: MovieDetailedViewModel.Factory

    private val args: MovieDetailedFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        creditsAdapter = CreditsAdapter()
        cinemaAdapter = CinemaAdapter {
        }

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
        movieDetailedViewModel.getMovieDetailedById(args.id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        creditsAdapter = null
        cinemaAdapter = null
    }

    private fun bindViewModel() {
        movieDetailedViewModel.movie.observe(viewLifecycleOwner) {
            setData(it)
        }

        movieDetailedViewModel.isLoading.observe(viewLifecycleOwner) {
            isLoading(it)
        }
    }

    private fun setData(movieDetailedUIModel: MovieDetailedUIModel) {
        with(viewBinding) {
            movieTitle.text = movieDetailedUIModel.title
            movieGenre.text =
                movieDetailedUIModel.genre.toString().replace("[", "").replace("]", "");
            movieOverview.text = movieDetailedUIModel.overView
            creditsAdapter?.updateCreditsList(movieDetailedUIModel.credits)
            cinemaAdapter?.updateCinemaList(movieDetailedUIModel.recommendations)

            Glide.with(requireContext())
                .load("${getString(R.string.image_url)}${movieDetailedUIModel.bannerPath}")
                .placeholder(R.drawable.ic_downloading_24).error(R.drawable.ic_error_download_24)
                .into(movieBackdrop)
            Glide.with(requireContext())
                .load("${getString(R.string.image_url)}${movieDetailedUIModel.posterPath}")
                .placeholder(R.drawable.ic_downloading_24).error(R.drawable.ic_error_download_24)
                .into(movieIcon)
        }
    }

    private fun isLoading(isLoading: Boolean) {
        viewBinding.progressBar.isVisible = isLoading
        viewBinding.movieDetailedGroup.isVisible = !isLoading
    }
}