package com.example.nirmitee_task.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nirmitee_task.R

class TodayFeaturedAdapter: RecyclerView.Adapter<TodayFeaturedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodayFeaturedAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_today_featured, parent, false)

        return TodayFeaturedAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodayFeaturedAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 7
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
    }
}