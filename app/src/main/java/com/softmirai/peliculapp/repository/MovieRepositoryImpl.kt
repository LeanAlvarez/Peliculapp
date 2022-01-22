package com.softmirai.peliculapp.repository

import com.softmirai.peliculapp.data.model.MovieList
import com.softmirai.peliculapp.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource) : MovieRepository{
    override suspend fun getUpcomingMovies(): MovieList = dataSourceRemote.getUpcomingMovies()

    override suspend fun getTopRatedMovies(): MovieList = dataSourceRemote.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList = dataSourceRemote.getPopularMovies()
}