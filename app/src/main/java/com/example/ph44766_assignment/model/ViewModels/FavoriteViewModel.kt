package com.example.ph44766_assignment.model.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ph44766_assignment.model.Favorites

class FavoriteViewModel : ViewModel() {
    private val _favorites = MutableLiveData<List<Favorites>>()
    val favorites: LiveData<List<Favorites>> = _favorites

    init {
        _favorites.value = listOf()  // Initialize with an empty list
    }

    fun addToFavorite(item: Favorites) {
        val currentList = _favorites.value ?: emptyList()
        _favorites.value = currentList + item
    }

    fun removeFromFavorite(item: Favorites) {
        val currentList = _favorites.value ?: emptyList()
        _favorites.value = currentList.filter { it.id != item.id }
    }
}