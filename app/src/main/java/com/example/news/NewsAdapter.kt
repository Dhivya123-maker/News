package com.example.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.news.databinding.NewsItemBinding
import java.util.*
import kotlin.collections.ArrayList

//class NewsAdapter(var ns:List<NewsModel>): RecyclerView.Adapter<MainViewHolder>() {
class NewsAdapter: RecyclerView.Adapter<MainViewHolder>() {
    var newslist: ArrayList<NewsModel> = ArrayList<NewsModel>()
    var news = mutableListOf<NewsModel>()
    fun setNewsList(News: List<NewsModel>) {
        this.news = News.toMutableList()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val newsList = news[position]
        holder.binding.titleName.text = newsList.title
        holder.binding.descriptionName.text = newsList.summary
    }


    fun filterList(filteredNames : ArrayList<NewsModel> ) {
        newslist = filteredNames
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return news.size
    }


    }


class MainViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
}