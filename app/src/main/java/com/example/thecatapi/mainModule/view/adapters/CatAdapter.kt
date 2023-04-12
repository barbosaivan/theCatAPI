package com.example.thecatapi.mainModule.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.thecatapi.R
import com.example.thecatapi.common.entities.Cats
import com.example.thecatapi.databinding.ItemCatBinding

/* 
* Project: TheCatAPI
* From: com.example.thecatapi.mainModule.view.adapters
* Create by Ivan Barbosa on 12/04/2023 at 3:58 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class CatAdapter (private var cats: MutableList<Cats>): RecyclerView.Adapter<CatAdapter.ViewHolder>(){

private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_cat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cats.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cat = cats[position]
        with(holder){
            binding.tvRaceName.text = cat.name
            binding.tvCountryOrigin.text = cat.origin
            binding.tvIntelligence.text = cat.intelligence
            Glide.with(context).load("https://cdn2.thecatapi.com/images/${cat.reference_image_id}.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgcat)
        }
    }

    fun setCats(cats: MutableList<Cats>) {
        this.cats = cats
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemCatBinding.bind(view)
    }
}
