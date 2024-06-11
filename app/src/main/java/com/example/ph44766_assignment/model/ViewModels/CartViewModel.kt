package com.example.ph44766_assignment.model.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ph44766_assignment.model.Carts

class CartViewModel : ViewModel() {
    private val _carts = MutableLiveData<List<Carts>>()
    val carts: LiveData<List<Carts>> = _carts
    fun addToCart(item: Carts) {
        val currentList = _carts.value ?: emptyList()
        _carts.value = currentList + item
    }

    fun removeFromCart(item: Carts) {
        val currentList = _carts.value ?: emptyList()
        _carts.value = currentList.filter { it.id != item.id }
    }


    fun increaseFoodCount(item: Carts) {
        val currentCount = _carts.value?.firstOrNull { it.id == item.id }?.quantity ?: 0
        updateCount(item, currentCount + 1)
    }

    fun decreaseFoodCount(item: Carts) {
        val currentCount = _carts.value?.firstOrNull { it.id == item.id }?.quantity ?: 0
        if (currentCount == 1) {
            removeFromCart(item)
        } else {
            updateCount(item, currentCount - 1)
        }
    }

    private fun updateCount(item: Carts, count: Int) {
        _carts.value = _carts.value?.map {
            if (it.id == item.id) {
                it.copy(quantity = count)
            } else {
                it
            }
        }
    }
}