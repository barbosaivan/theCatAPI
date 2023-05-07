package com.example.thecatapi.mainModule.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
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
        setUpButtons()
    }

    private fun setUpButtons() {
        binding.btnDog.setOnClickListener {_ ->
            with(binding){
                btnDog.setTextColor(getColor(R.color.white))
                setColorFilter(btnDog.compoundDrawables, getColor(R.color.dark_orange))
                btnCat.setTextColor(getColor(R.color.gray_green))
                setColorFilter(btnCat.compoundDrawables, null)
            }
        }
        binding.btnCat.setOnClickListener {_ ->
            with(binding){
                btnCat.setTextColor(getColor(R.color.white))
                setColorFilter(btnCat.compoundDrawables, getColor(R.color.dark_orange))
                btnDog.setTextColor(getColor(R.color.gray_green))
                setColorFilter(btnDog.compoundDrawables, null)
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun setColorFilter(drawables: Array<Drawable>, @ColorInt color: Int?) {
        for (drawable in drawables) {
            try {
                if (color != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
                    } else {
                        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                    }
                } else {
                    drawable.clearColorFilter()
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
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
            try {
                startActivity(webSite)
            }catch (e: ActivityNotFoundException){
                e.printStackTrace()
                Toast.makeText(this, getString(R.string.error_accessing_browser), Toast.LENGTH_LONG).show()
            }
        }
    }

    //OnclickListener
    override fun onClick(cat: Cats) {
        try {
            goToWebSite(cat.vetstreet_url)
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Toast.makeText(this, getString(R.string.error_url_inexistente), Toast.LENGTH_LONG).show()
        }
    }
}