package com.softmirai.peliculapp.repository

import com.softmirai.peliculapp.data.model.MovieList

interface MovieRepository {

    /*defino los metodos que luego voy a ocupar en el data source

     utilizo co-rutinas para ir a buscar la info al server

     */

    suspend fun getUpcomingMovies(): MovieList

    suspend fun getTopRatedMovies(): MovieList

    suspend fun getPopularMovies(): MovieList
}