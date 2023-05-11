package com.artemiod.challengeandroidssr.presentation.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.artemiod.challengeandroidssr.R
import com.artemiod.challengeandroidssr.databinding.FragmentMainBinding
import com.artemiod.challengeandroidssr.presentation.viewmodel.ApiStatus
import com.artemiod.challengeandroidssr.presentation.viewmodel.CatViewModel
import java.lang.ClassCastException

class MainFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CatViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CatAdapter

    private lateinit var searchView: SearchView

    private lateinit var listener: SelectedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = try {
            context as SelectedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context debe implementar el listener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CatAdapter()
        recyclerView = binding.rvCats
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = adapter

        observeApiStatus()
        observeListPokemon()
        searchBreed()
        onClickItem()
    }

    private fun observeApiStatus() {
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when(status) {
                ApiStatus.LOADING -> {
                    binding.statusOffline.visibility = View.GONE
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.rvCats.visibility = View.GONE
                }
                ApiStatus.ERROR -> {
                    binding.statusOffline.visibility = View.VISIBLE
                    binding.progressCircular.visibility = View.GONE
                    binding.rvCats.visibility = View.GONE
                }
                ApiStatus.DONE -> {
                    binding.statusOffline.visibility = View.GONE
                    binding.progressCircular.visibility = View.GONE
                    binding.rvCats.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun searchBreed() {
        searchView = binding.searchView
        searchView.setOnQueryTextListener(this)
    }

    private fun observeListPokemon() {
        viewModel.catList.observe(viewLifecycleOwner) {
            adapter.setImages(it)
        }
    }

    private fun onClickItem() {
        adapter.onItemClickListener = {
            listener.OnSelected(img = it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // metodos de SearchView
    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            viewModel.searchCatBreed(raza = query)
        }
        hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
    }

}