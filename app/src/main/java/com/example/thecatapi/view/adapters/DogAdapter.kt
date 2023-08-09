package com.example.thecatapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thecatapi.R
import com.example.thecatapi.common.entities.Dogs
import com.example.thecatapi.databinding.ItemBreedBinding

/* 
* Project: TheCatAPI
* From: com.example.thecatapi.view.adapters
* Create by Ivan Barbosa on 11/05/2023 at 6:26 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/

class DogAdapter(private var dogs: MutableList<Dogs>, private var listener: OnClickListenerDog) :
    RecyclerView.Adapter<DogAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_breed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogAdapter.ViewHolder, position: Int) {
        val dog = dogs[position]
        with(holder) {
            setListener(dog)
            binding.tvRaceName.text = dog.name
            com.bumptech.glide.Glide.with(context)
                .load("https://cdn2.thedogapi.com/images/${dog.reference_image_id}.jpg")
                .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgcat)
        }
    }

    override fun getItemCount(): Int = dogs.size
    fun addDogs(dogs: MutableList<Dogs>) {
        this.dogs.addAll(dogs)
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBreedBinding.bind(view)
        fun setListener(dog: Dogs) {
            with(binding.root) {
                setOnClickListener { listener.onClick(dog) }
            }
        }
    }
}