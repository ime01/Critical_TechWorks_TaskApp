package com.example.criticaltechworkstaskapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.criticaltechworkstaskapp.R


/**
 * A simple [Fragment] subclass.
 * Use the [HeadLineDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class HeadLineDetailsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_head_line_details, container, false)
    }


}