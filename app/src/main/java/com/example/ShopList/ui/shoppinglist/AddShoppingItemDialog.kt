package com.example.ShopList.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.ShopList.data.db.entites.ShoppingItem
import com.example.myapplication.R
import kotlinx.android.synthetic.main.dialog_add_items.*

class AddShoppingItemDialog(context: Context, var addDialogListener: AddDialogListener) :
        AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_items)

        tvAdd.setOnClickListener {
            val name = etName.text.toString()
            val amount = etAmount.text.toString()
            if (name.isNullOrEmpty() || amount.isNullOrEmpty()) {
                Toast.makeText(context, "Please enter the info", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
                val item = ShoppingItem(name, amount.toInt())
                addDialogListener.onAddButtonClicked(item)
                dismiss()
            }


        tvCancel.setOnClickListener {
            cancel()
        }
    }
}