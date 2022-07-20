package dev.jamile.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import dev.jamile.newsapp.common.observerEvent
import dev.jamile.newsapp.databinding.ActivityMainBinding
import dev.jamile.newsapp.domain.model.News
import dev.jamile.newsapp.ui.newsList.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<NewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel.fetchNews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.newList.observerEvent(this@MainActivity,
            onSuccess = {
                val newsAdapter = NewsAdapter(it)
                binding.recyclerview.adapter = newsAdapter
            },
            onError = {
                Toast.makeText(this, "Sorry :( deu ruim aki", Toast.LENGTH_SHORT).show()
            }
        )
    }
}