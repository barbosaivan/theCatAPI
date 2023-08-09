package com.example.thecatapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecatapi.R
import com.example.thecatapi.common.entities.Dogs
import com.example.thecatapi.databinding.FragmentDogBinding
import com.example.thecatapi.view.adapters.DogAdapter
import com.example.thecatapi.view.adapters.OnClickListenerDog
import com.example.thecatapi.viewModel.DogViewModel

class DogFragment : Fragment(), OnClickListenerDog {
    private var _binding: FragmentDogBinding? = null
    private val binding get() = _binding!!

    private var loading = true
    private var page = 0

    private lateinit var dogAdapter: DogAdapter
    private lateinit var dogViewModel: DogViewModel
    private lateinit var gridLayout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogBinding.inflate(inflater, container, false)

        setUpRecycler()
        sepUpViewModel()
        setRecyclerViewScrollListener()

        return binding.root
    }

    private fun sepUpViewModel() {
        dogViewModel = ViewModelProvider(this)[DogViewModel::class.java]
        dogViewModel.getDogs(page.toString()).observe(viewLifecycleOwner) {
            dogAdapter.addDogs(it)
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

    private fun setRecyclerViewScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    with(binding.recyclerView) {
                        val visibleItem = (layoutManager as? LinearLayoutManager)?.childCount!!
                        val totalItemCount = (layoutManager as? LinearLayoutManager)?.itemCount!!
                        val pastVisibleItem =
                            (layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()!!
                        if (loading && visibleItem + pastVisibleItem >= totalItemCount) {
                            loading = false
                            page++
                            dogViewModel.loadDogs(page.toString())
                            loading = true
                        }
                    }
                }
            }
        })
    }

    override fun onClick(dogs: Dogs) {
        TODO("Not yet implemented")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}