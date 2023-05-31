package com.example.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.api.ApiFactory
import com.example.movies.pojo.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val movies = MutableLiveData<List<Movie>>()
    private val isLoading = MutableLiveData<Boolean>()
    val getMovies: LiveData<List<Movie>> get() = movies
    val getIsLoading: LiveData<Boolean> get() = isLoading
    private var page = 1

    init {
        loadMovies()
    }

    fun loadMovies() {
        isLoading.value?.let { if (it) return }
        val disposable = ApiFactory.apiService.loadMovie(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doAfterSuccess { isLoading.value = false }
            .subscribe({
                val movieListAdded = movies.value?.toMutableList()
                if (movieListAdded != null) {
                    movieListAdded.addAll(it.movies)
                    movies.value = movieListAdded
                } else {
                    movies.value = it.movies
                }
                page++
            }, { Log.e("MainViewModel", "loadMovies: $it") })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}