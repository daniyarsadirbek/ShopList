package com.example.ShopList.data.repositories

import com.example.ShopList.data.db.ShoppingDatabase
import com.example.ShopList.data.db.entites.ShoppingItem

class ShoppingRepository(
        private val database: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = database.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = database.getShoppingDao().delete(item)

    fun getAllShoppingItems() = database.getShoppingDao().getAllShoppingItems()
}