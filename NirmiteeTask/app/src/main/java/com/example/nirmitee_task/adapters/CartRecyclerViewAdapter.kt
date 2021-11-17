package com.example.nirmitee_task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nirmitee_task.R

class CartRecyclerViewAdapter: RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return CartRecyclerViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartRecyclerViewAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 2
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }

}