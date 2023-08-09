package com.example.thecatapi.view

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.example.thecatapi.R
import com.example.thecatapi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpButtons()
    }

    private fun setUpButtons() {
        binding.btnCat.setTextColor(getColor(R.color.white))
        binding.btnCat.setOnClickListener {
            buttonCAt()
            with(binding) {
                btnCat.setTextColor(getColor(R.color.white))
                setColorFilter(btnCat.compoundDrawables, getColor(R.color.dark_orange))
                btnDog.setTextColor(getColor(R.color.gray_green))
                setColorFilter(btnDog.compoundDrawables, null)
            }
        }
        binding.btnDog.setOnClickListener {
            buttonDog()
            with(binding) {
                btnDog.setTextColor(getColor(R.color.white))
                setColorFilter(btnDog.compoundDrawables, getColor(R.color.dark_orange))
                btnCat.setTextColor(getColor(R.color.gray_green))
                setColorFilter(btnCat.compoundDrawables, null)
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

    private fun buttonCAt() {
        val fragment = CatFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.breeds_fragment, fragment)
        binding.tvTitle.text = getString(R.string.tv_title)
        binding.tvDescriptionTitle.text = getString(R.string.description_title)
        fragmentTransaction.commit()
    }

    private fun buttonDog() {
        val fragment = DogFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.breeds_fragment, fragment)
        binding.tvTitle.text = getString(R.string.tv_title_dogs)
        binding.tvDescriptionTitle.text = getString(R.string.description_title_dogs)
        fragmentTransaction.commit()
    }
}