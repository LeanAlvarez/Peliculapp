package com.softmirai.peliculapp.ui.movie.adapters.concat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softmirai.peliculapp.core.BaseConcatHolder
import com.softmirai.peliculapp.databinding.TopRatedMoviesRowBinding
import com.softmirai.peliculapp.databinding.UpcomingMovieRowBinding

class TopRatedConcatAdapter(private val movieAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemBinding = TopRatedMoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ConcatViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder){
            is ConcatViewHolder -> holder.bind(movieAdapter)
        }
    }

    override fun getItemCount(): Int = 1

    private inner class ConcatViewHolder(val binding: TopRatedMoviesRowBinding): BaseConcatHolder<MovieAdapter>(binding.root){
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRatedMovies.adapter = adapter
        }

    }

}