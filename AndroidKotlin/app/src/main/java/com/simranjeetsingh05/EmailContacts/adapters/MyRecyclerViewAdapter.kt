package com.simranjeetsingh05.EmailContacts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.simranjeetsingh05.EmailContacts.R
import com.simranjeetsingh05.EmailContacts.database.People
import com.simranjeetsingh05.EmailContacts.databinding.ListItemBinding

class MyRecyclerViewAdapter(private val clickListener: (People)->Unit) : RecyclerView.Adapter<MyViewHolder>() {
    private val peopleList = ArrayList<People>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        inflating layout with item list
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item,parent,false)

        return MyViewHolder((binding))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        to display data
        //position=count
//       MyViewHolder class implemented below i e:-name and email

        holder.bind(peopleList[position],clickListener)

    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    fun setList(people: List<People>){
        peopleList.clear()
        peopleList.addAll(people)
    }


}

class  MyViewHolder(val binding:ListItemBinding) :RecyclerView.ViewHolder(binding.root){


    fun bind(people: People,clickListener: (People)->Unit){
        binding.nameTextView.text = people.name
        binding.emailTextView.text = people.email
        binding.listItemLayout.setOnClickListener {
            clickListener(people)
        }
    }
}