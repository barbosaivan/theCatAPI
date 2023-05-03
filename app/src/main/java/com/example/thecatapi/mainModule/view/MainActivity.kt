package com.example.thecatapi.mainModule.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thecatapi.R
import com.example.thecatapi.common.entities.Cats
import com.example.thecatapi.databinding.ActivityMainBinding
import com.example.thecatapi.mainModule.view.adapters.CatAdapter
import com.example.thecatapi.mainModule.view.adapters.OnClickListener
import com.example.thecatapi.mainModule.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), OnClickListener {
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
        catAdapter = CatAdapter(mutableListOf(), this@MainActivity)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.catAdapter
        }
    }

    private fun goToWebSite(url: String) {
        if (url.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_exist_url), Toast.LENGTH_LONG).show()
        } else {
            val webSite = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
            if (webSite.resolveActivity(packageManager) != null)
                startActivity(webSite)
            else
                Toast.makeText(this, getString(R.string.error_accessing_browser), Toast.LENGTH_LONG).show()
        }
    }

    //OnclickListener
    override fun onClick(cat: Cats) {
        if (cat.vetstreet_url != null){
            goToWebSite(cat.vetstreet_url)
        }
    }
}