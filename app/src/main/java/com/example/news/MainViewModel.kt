package com.example.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val newsList = MutableLiveData<List<NewsModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllNews() {
        val response = repository.getAllNews()
        response.enqueue(object : Callback<List<NewsModel>> {
            override fun onResponse(call: Call<List<NewsModel>>, response: Response<List<NewsModel>>) {
                newsList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<NewsModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })


    }
}