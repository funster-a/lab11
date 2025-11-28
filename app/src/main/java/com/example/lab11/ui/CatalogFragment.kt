package com.example.lab11.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab11.R

class CatalogFragment : Fragment(R.layout.fragment_catalog) {
    private val viewModel: CatalogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCatalog)
        val adapter = ItemAdapter { item ->
            // This part assumes you have a class named ItemAdapter and a sealed class for State
            // If they are missing, that would be the next error to fix.
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
                    // You can show a ProgressBar here
                }
                is CatalogViewModel.State.Success -> {
                    // This assumes your adapter has a 'submit' method
                    adapter.submit(state.items)
                }
                is CatalogViewModel.State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
