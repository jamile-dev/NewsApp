package dev.jamile.newsapp.newsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jamile.newsapp.databinding.NewsItemBinding
import dev.jamile.newsapp.model.News

class NewsAdapter(private val newsList: List<Any>) :
    RecyclerView.Adapter<NewsAdapter.HoursViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder) {
            with(newsList[position]) {
                binding.topLearnerName.text = name
                val hours = "$hours learning hours, $country"
                binding.topLearnerTime.text = hours
                Glide.with(holder.itemView.context)
                    .load(newsUrl)
                    .into(binding.topLearnerImage)

                holder.itemView.setOnClickListener {
                    Toast.makeText(
                        holder.itemView.context, hours,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    inner class NewsViewHolder(val binding: HoursListItemsBinding) :
        RecyclerView.ViewHolder(binding.root)

}