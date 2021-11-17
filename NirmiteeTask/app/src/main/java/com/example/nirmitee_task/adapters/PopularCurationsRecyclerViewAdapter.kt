package com.example.nirmitee_task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nirmitee_task.R

class PopularCurationsRecyclerViewAdapter:
    RecyclerView.Adapter<PopularCurationsRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularCurationsRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular_curations, parent, false)

        return PopularCurationsRecyclerViewAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PopularCurationsRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
    }

    override fun getItemCount(): Int {
        return 11
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }

}