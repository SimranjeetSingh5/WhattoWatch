package com.example.nirmitee_task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nirmitee_task.R

class RestaurantsRecyclerViewAdapter :
    RecyclerView.Adapter<RestaurantsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantsRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurants, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RestaurantsRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
    }

    override fun getItemCount(): Int {
        return 5
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }
}