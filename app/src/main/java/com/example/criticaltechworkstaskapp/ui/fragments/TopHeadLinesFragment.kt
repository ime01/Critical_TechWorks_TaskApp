package com.example.criticaltechworkstaskapp.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criticaltechworkstaskapp.BuildConfig
import com.example.criticaltechworkstaskapp.common.Constants
import com.example.criticaltechworkstaskapp.common.Status
import com.example.criticaltechworkstaskapp.common.showSnackbar
import com.example.criticaltechworkstaskapp.databinding.FragmentTopHeadLinesBinding
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.presentation.adapter.NewsAdapter
import com.example.criticaltechworkstaskapp.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadLinesFragment : Fragment() {

    private var _binding: FragmentTopHeadLinesBinding? = null
    private val binding by lazy { _binding!! }
    lateinit var newsAdapter  : NewsAdapter
    private val viewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTopHeadLinesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews(checkFlavourReturnNewsSource(), BuildConfig.API_KEY)

        showWelcomeMarqueeText()
        observeState()
        newsAdapter = NewsAdapter{
            transitionToDetailView(it)
        }
    }


    private fun showWelcomeMarqueeText() {

        binding.apply {

            welcomeTextMarquee.apply {
                setSingleLine()
                ellipsize = TextUtils.TruncateAt.MARQUEE
                marqueeRepeatLimit = -1
                isSelected = true
            }
        }
    }

    fun observeState(){

        binding.apply {

            viewModel.newsFromNetwork.observe(viewLifecycleOwner) { news ->

                news?.also {
                    when (it.status) {
                        Status.ERROR -> {

                            shimmerFrameLayout.stopShimmer()
                            shimmerFrameLayout.visibility = View.GONE
                            errorImage.isVisible = true
                            errorText.text = it.message
                            errorText.isVisible = true
                            showSnackbar(welcomeTextMarquee, it.message!!)

                        }
                        Status.LOADING -> {
                            shimmerFrameLayout.startShimmer()
                            shimmerFrameLayout.visibility = View.VISIBLE

                        }

                        Status.SUCCESS -> {

                            val headLinesSortedByDate = it.data?.sortedBy { it.publishedAt }
                            headLinesSortedByDate?.let { sortedNews ->
                                loadRecyclerView(sortedNews)
                            }
                        }

                    }
                }

            }
        }

    }


    private fun loadRecyclerView(news: List<News>) {

        binding.apply {
            errorImage.isVisible = false
            newsAdapter.submitList(news)
            rvList.layoutManager = LinearLayoutManager(requireContext())
            rvList.adapter = newsAdapter
            val decoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
            rvList.addItemDecoration(decoration)

            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE

        }
    }


    private fun transitionToDetailView(news: News) {

        val action = TopHeadLinesFragmentDirections.actionTopHeadLinesFragmentToHeadLineDetailsFragment(news)
        Navigation.findNavController(requireView()).navigate(action)

    }

    private fun checkFlavourReturnNewsSource():String{
        return   if(BuildConfig.FLAVOR.equals(Constants.REUTERS_FLAVOUR)) {
            Constants.REUTERS_FLAVOUR_NEWS_SOURCE
        } else if (BuildConfig.FLAVOR.equals(Constants.GOOGLE_FLAVOUR)) {
            Constants.GOOGLE_FLAVOUR_NEWS_SOURCE
        }else if (BuildConfig.FLAVOR.equals(Constants.CNN_FLAVOUR)){
            Constants.CNN_FLAVOUR_NEWS_SOURCE
        }else Constants.MAIN_APP_NEWS_SOURCE
    }





}