package dev.jamile.newsapp.ui.newsList

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
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
                    .placeholder(android.R.drawable.ic_menu_agenda)
                    .centerCrop()
                    .into(binding.newsImage)

                holder.itemView.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(it.context, intent, null)
                }
            }
        }
    }

    inner class NewsViewHolder(val binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}