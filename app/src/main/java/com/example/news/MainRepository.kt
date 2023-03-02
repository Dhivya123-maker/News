package com.example.news


class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllNews() = retrofitService.getAllNews()
}
