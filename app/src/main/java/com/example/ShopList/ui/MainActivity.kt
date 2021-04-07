package com.example.ShopList.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ShopList.data.db.entites.ShoppingItem
import com.example.ShopList.other.ShoppingItemAdapter
import com.example.ShopList.ui.shoppinglist.AddDialogListener
import com.example.ShopList.ui.shoppinglist.AddShoppingItemDialog
import com.example.ShopList.ui.shoppinglist.ShoppingViewModel
import com.example.ShopList.ui.shoppinglist.ShoppingViewModelFactory
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rv_shopping_items.layoutManager = LinearLayoutManager(this)
        rv_shopping_items.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(
                    this,
                    object : AddDialogListener {
                        override fun onAddButtonClicked(item: ShoppingItem) {
                            viewModel.upsert(item)
                        }
                    }).show()
        }
    }
}