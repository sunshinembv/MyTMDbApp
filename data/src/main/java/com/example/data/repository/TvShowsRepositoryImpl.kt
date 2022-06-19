package com.example.data.repository

import com.example.domain.models.show.Show
import com.example.domain.repository.TvShowsRepository

class TvShowsRepositoryImpl : TvShowsRepository {

    private val tvShowList = listOf(
        Show(
            "1",
            "Title1",
            "Date1",
            "60",
            "https://images.unsplash.com/photo-1433086966358-54859d0ed716?ixlib=rb-1.2.1" +
                "&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
        ),
        Show(
            "2",
            "Title2",
            "Date2",
            "70",
            "https://images.unsplash.com/photo-1540206395-68808572332f?ixlib=rb-1.2.1" +
                "&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=652&q=80"
        ),
        Show(
            "3",
            "Title3",
            "Date3",
            "80",
            "https://images.unsplash.com/photo-1586348943529-beaae6c28db9?ixlib=rb-1.2.1" +
                "&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=830&q=80"
        ),
        Show(
            "4",
            "Title4",
            "Date4",
            "80",
            "https://images.unsplash.com/photo-1455218873509-8097305ee378?ixlib=rb-1.2.1" +
                "&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
        ),
        Show(
            "5",
            "Title5",
            "Date5",
            "90",
            "https://images.unsplash.com/photo-1546514355-7fdc90ccbd03?ixlib=rb-1.2.1" +
                "&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
        ),
    )

    override fun getPopularTvShows(): List<Show> {
        return tvShowList
    }

    override fun getTopRatingsTvShows(): List<Show> {
        return tvShowList
    }

    override fun getUpcomingTvShows(): List<Show> {
        return tvShowList
    }
}
