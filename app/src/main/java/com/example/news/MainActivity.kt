package com.example.news

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.news.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = NewsAdapter()

  var newsList :List<NewsModel> = ArrayList<NewsModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, NewsViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerView.adapter = adapter


        viewModel.newsList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setNewsList(it)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getAllNews()



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return false
            }
        })


    }


    private fun search(text: String) {

        val filteredlist: ArrayList<NewsModel> = ArrayList<NewsModel>()

        for (item in newsList) {
            if (item.title.toLowerCase().contains(text.lowercase()) || item.summary.toLowerCase().contains(text.lowercase()))
             {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(this,"No match found...",Toast.LENGTH_SHORT).show()

        } else {
           adapter.filterList(filteredlist)
        }
    }


}




