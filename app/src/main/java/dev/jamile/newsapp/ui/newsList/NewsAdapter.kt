package dev.jamile.newsapp.ui.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jamile.newsapp.databinding.NewsItemBinding
import dev.jamile.newsapp.domain.model.News

class NewsAdapter(private val newsList: List<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(newsList[position]) {
                binding.let {
                    it.sourceName.text = this.title
                    it.description.text = this.description
                }

                Glide.with(holder.itemView.context)
                    .load(urlToImage)
                    .into(binding.newsImage)

                holder.itemView.setOnClickListener {

                }
            }
        }
    }

    inner class NewsViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}