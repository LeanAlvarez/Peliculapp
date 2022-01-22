package com.softmirai.peliculapp.data.remote

import com.softmirai.peliculapp.application.AppConstants
import com.softmirai.peliculapp.data.model.MovieList
import com.softmirai.peliculapp.repository.WebService

class RemoteMovieDataSource(private val webService: WebService) {
    //creo los metodos que van a ir a buscar información al servidor de cada una de las peliculas

    suspend fun getUpcomingMovies(): MovieList = webService.getUpcomingMovies(AppConstants.API_KEY)


    suspend fun getTopRatedMovies(): MovieList = webService.getTopRatedMovies(AppConstants.API_KEY)


    suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)


}