package com.example.ph44766_assignment.model.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ph44766_assignment.model.Populars

class PopularViewModel : ViewModel() {

    private val _popular = MutableLiveData<List<Populars>>()
    val populars: LiveData<List<Populars>> = _popular
    fun addToFavorites(item: Populars) {
        val currentList = _popular.value ?: emptyList()
        _popular.value = currentList + item
    }

    fun removeFromFavorites(item: Populars) {
        val currentList = _popular.value ?: emptyList()
        _popular.value = currentList.filter { it.id != item.id }
    }

    fun addToCart(item: Populars) {
        val currentList = _popular.value ?: emptyList()
        _popular.value = currentList + item
    }

    fun removeFromCart(item: Populars) {
        val currentList = _popular.value ?: emptyList()
        _popular.value = currentList.filter { it.id != item.id }
    }
}
