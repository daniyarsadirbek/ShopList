package com.example.ShopList.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ShopList.data.db.entites.ShoppingItem
import com.example.ShopList.ui.shoppinglist.ShoppingViewModel
import com.example.myapplication.R
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
        var items: List<ShoppingItem>,
        private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]


        holder.apply {
            itemView.apply {
                tvName.text = curShoppingItem.name
                tvAmount.text = curShoppingItem.amount.toString()
                ivDelete.setOnClickListener {
                    viewModel.delete(curShoppingItem)
                    Toast.makeText(context, "${items[position].name} has just been deleted", Toast.LENGTH_SHORT).show()
                }
                ivPlus.setOnClickListener {
                    curShoppingItem.amount++
                    viewModel.upsert(curShoppingItem)
                }
                ivMinus.setOnClickListener {
                    if (curShoppingItem.amount > 0) {
                        curShoppingItem.amount--
                        viewModel.upsert(curShoppingItem)
                    }
                }
            }
        }
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}