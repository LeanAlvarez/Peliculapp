package com.softmirai.peliculapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.softmirai.peliculapp.core.Resource
import com.softmirai.peliculapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel(private val repo: MovieRepository ): ViewModel() {
    //metodo para ir a buscar las ultimas peliculas

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){

        emit(Resource.Loading())

        try {
            emit(Resource.Success(Triple(repo.getUpcomingMovies(), repo.getTopRatedMovies(), repo.getPopularMovies())))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }



}


class MovieViewModelFactory(private val repo: MovieRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}