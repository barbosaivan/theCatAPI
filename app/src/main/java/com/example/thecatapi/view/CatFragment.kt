package com.example.thecatapi.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecatapi.R
import com.example.thecatapi.common.entities.Cats
import com.example.thecatapi.databinding.FragmentCatBinding
import com.example.thecatapi.view.adapters.CatAdapter
import com.example.thecatapi.view.adapters.OnClickListenerCat
import com.example.thecatapi.viewModel.CatViewModel


class CatFragment : Fragment(), OnClickListenerCat {

    private var _binding: FragmentCatBinding? = null
    private val binding get() = _binding!!

    private var loading = true
    private var page = 0

    private lateinit var catAdapter: CatAdapter
    private lateinit var catViewModel: CatViewModel
    private lateinit var gridLayout: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatBinding.inflate(inflater, container, false)

        setUpRecycler()
        setUpViewModel()
        setUpRecyclerViewScrollListener()

        return binding.root
    }

    private fun setUpViewModel() {
        catViewModel = ViewModelProvider(this)[CatViewModel::class.java]
        catViewModel.getCats(page.toString()).observe(viewLifecycleOwner) {
            catAdapter.setCats(it)
        }

    }

    private fun setUpRecycler() {
        catAdapter = CatAdapter(mutableListOf(), this)
        gridLayout =
            GridLayoutManager(requireContext(), resources.getInteger(R.integer.main_columns))
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = gridLayout
            adapter = this@CatFragment.catAdapter
        }
    }

    private fun setUpRecyclerViewScrollListener() {
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
                            catViewModel.loadCats(page.toString())
                            loading = true
                        }
                    }
                }
            }
        })
    }

    private fun goToWebSite(url: String) {
        if (url.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.error_exist_url), Toast.LENGTH_LONG)
                .show()
        } else {
            val webSite = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
            try {
                startActivity(webSite)
            } catch (e: ActivityNotFoundException) {
                e.printStackTrace()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_accessing_browser),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    //OnclickListener
    override fun onClick(cat: Cats) {
        try {
            goToWebSite(cat.vetstreet_url)
        } catch (e: NullPointerException) {
            e.printStackTrace()
            Toast.makeText(
                requireContext(),
                getString(R.string.error_url_inexistente),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}