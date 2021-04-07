package com.example.ShopList.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.ShopList.data.db.entites.ShoppingItem
import com.example.ShopList.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(
        private val repository: ShoppingRepository
) : ViewModel() {

    fun upsert(item: ShoppingItem) =
            GlobalScope.launch {
                repository.upsert(item)
            }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}