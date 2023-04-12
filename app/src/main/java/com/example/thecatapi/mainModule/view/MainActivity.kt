package com.example.thecatapi.mainModule.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecatapi.databinding.ActivityMainBinding
import com.example.thecatapi.mainModule.view.adapters.CatAdapter
import com.example.thecatapi.mainModule.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var catAdapter: CatAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       sepUpViewModel()
        setUpRecycler()
    }

    private fun sepUpViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getCats().observe(this){
            catAdapter.setCats(it)
        }
    }

    private fun setUpRecycler() {
        catAdapter = CatAdapter(mutableListOf())
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.catAdapter
        }
    }
}