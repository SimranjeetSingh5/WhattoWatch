package com.simranjeetsingh05.EmailContacts.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.simranjeetsingh05.EmailContacts.R
import com.simranjeetsingh05.EmailContacts.adapters.MyRecyclerViewAdapter
import com.simranjeetsingh05.EmailContacts.database.People
import com.simranjeetsingh05.EmailContacts.database.PeopleDatabase
import com.simranjeetsingh05.EmailContacts.databinding.ActivityMainBinding
import com.simranjeetsingh05.EmailContacts.repository.PeopleRepository
import com.simranjeetsingh05.EmailContacts.viewmodel.PeopleViewModel
import com.simranjeetsingh05.EmailContacts.viewmodel.PeopleViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var peopleViewModel: PeopleViewModel
    private lateinit var adapter: MyRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = PeopleDatabase.getInstance(application).peopleDAO
        val repository = PeopleRepository(dao)
        val factory = PeopleViewModelFactory(repository)
        peopleViewModel = ViewModelProvider(this,factory).get(PeopleViewModel::class.java)
        binding.myViewModel = peopleViewModel
        binding.lifecycleOwner = this
        initRecyclerView()

        peopleViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let{
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })

    }
    private fun initRecyclerView(){
        binding.peopleRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerViewAdapter({ selectedItem: People -> listItemClicked(selectedItem) })
        binding.peopleRecyclerView.adapter = adapter
        displayPeopleList()

    }

    private fun displayPeopleList(){
        peopleViewModel.people.observe(this, Observer {
            Log.i("MYTAG",it.toString())
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(people: People){
//        Toast.makeText(this,"Selected name is ${people.name}",Toast.LENGTH_LONG).show()
        peopleViewModel.initUpdateAndDelete((people))

    }
}