package com.example.lab11.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.lab11.R

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemId = args.itemId
        val itemTitle = args.itemTitle

        val titleTextView = view.findViewById<TextView>(R.id.tvTitle)
        val idTextView = view.findViewById<TextView>(R.id.tvId)

        titleTextView.text = itemTitle
        idTextView.text = "ID: $itemId"
    }
}
