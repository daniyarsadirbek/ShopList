package com.example.ShopList.ui.shoppinglist

import com.example.ShopList.data.db.entites.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}