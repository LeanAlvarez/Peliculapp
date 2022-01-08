package com.softmirai.peliculapp.repository

import com.softmirai.peliculapp.data.model.MovieList

class MovieRepositoryImpl : MovieRepository{
    override suspend fun getUpcomingMovies(): MovieList {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRatedMovies(): MovieList {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularMovies(): MovieList {
        TODO("Not yet implemented")
    }
}