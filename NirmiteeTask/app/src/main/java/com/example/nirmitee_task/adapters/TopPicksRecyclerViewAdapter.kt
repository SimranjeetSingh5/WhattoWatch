package com.example.nirmitee_task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nirmitee_task.R

class TopPicksRecyclerViewAdapter: RecyclerView.Adapter<TopPicksRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPicksRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_top_picks, parent, false)

        return TopPicksRecyclerViewAdapter.ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }


    override fun getItemCount(): Int {
        return 10
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }


}