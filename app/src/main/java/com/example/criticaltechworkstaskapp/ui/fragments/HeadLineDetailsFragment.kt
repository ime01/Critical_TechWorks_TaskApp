package com.example.criticaltechworkstaskapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.criticaltechworkstaskapp.R
import com.example.criticaltechworkstaskapp.common.formatDateTime
import com.example.criticaltechworkstaskapp.databinding.FragmentHeadLineDetailsBinding
import com.example.criticaltechworkstaskapp.domian.model.News

class HeadLineDetailsFragment : Fragment() {

    private var _binding: FragmentHeadLineDetailsBinding? = null
    private val binding by lazy { _binding!! }
    private var news: News? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = HeadLineDetailsFragmentArgs.fromBundle(it).newsModel
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadLineDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            news?.apply {

                dtitle.text = title
                ddescription.text = description
                dcontent.text = content
                ddate.text =  formatDateTime(publishedAt)

                Glide.with(imageDetailLarge)
                    .load(news?.urlToImage)
                    .placeholder(R.drawable.ic_baseline_newspaper_24)
                    .error(R.drawable.ic_baseline_error_outline_24)
                    .fallback(R.drawable.ic_baseline_newspaper_24)
                    .into(imageDetailLarge)
            }
        }

    }


}