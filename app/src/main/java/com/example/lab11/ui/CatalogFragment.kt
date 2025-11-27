package com.example.app.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private val viewModel: CatalogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCatalog)
        val adapter = ItemAdapter { item ->
            // Переход через Safe Args
            val action = CatalogFragmentDirections.actionCatalogToDetails(
                itemId = item.id,
                itemTitle = item.title
            )
            findNavController().navigate(action)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when(state) {
                is CatalogViewModel.State.Loading -> {
                    // Можно показать ProgressBar
                }
                is CatalogViewModel.State.Success -> {
                    adapter.submit(state.items)
                }
                is CatalogViewModel.State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}