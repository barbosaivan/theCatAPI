package com.example.thecatapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.thecatapi.R
import com.example.thecatapi.common.entities.Dogs
import com.example.thecatapi.databinding.FragmentDogBinding
import com.example.thecatapi.view.adapters.DogAdapter
import com.example.thecatapi.view.adapters.OnClickListenerDog
import com.example.thecatapi.viewModel.DogViewModel

class DogFragment : Fragment(), OnClickListenerDog {
    private var _binding: FragmentDogBinding? = null
    private val binding get() = _binding!!

    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogViewModel: DogViewModel
    private lateinit var gridLayout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogBinding.inflate(inflater, container, false)

        setUpRecycler()
        sepUpViewModel()

        return binding.root
    }

    private fun sepUpViewModel() {
        dogViewModel = ViewModelProvider(this)[DogViewModel::class.java]
        dogViewModel.getDogs().observe(viewLifecycleOwner) {
            dogAdapter.setDogs(it)
        }

    }

    private fun setUpRecycler() {
        dogAdapter = DogAdapter(mutableListOf(), this)
        gridLayout =
            GridLayoutManager(requireContext(), resources.getInteger(R.integer.main_columns))
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = gridLayout
            adapter = this@DogFragment.dogAdapter
        }
    }

    override fun onClick(dogs: Dogs) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}