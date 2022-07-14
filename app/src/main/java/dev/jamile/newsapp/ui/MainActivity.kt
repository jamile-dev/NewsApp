package dev.jamile.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.jamile.newsapp.databinding.ActivityMainBinding
import dev.jamile.newsapp.domain.model.News
import dev.jamile.newsapp.ui.newsList.NewsAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val newsAdapter = NewsAdapter(arrayListOf<News>())
        binding.recyclerview.adapter = newsAdapter
    }
}