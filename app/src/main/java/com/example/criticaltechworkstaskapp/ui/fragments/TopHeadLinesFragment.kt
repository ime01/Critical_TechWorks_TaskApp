package com.example.criticaltechworkstaskapp.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.criticaltechworkstaskapp.BuildConfig
import com.example.criticaltechworkstaskapp.R
import com.example.criticaltechworkstaskapp.common.Constants
import com.example.criticaltechworkstaskapp.common.Status
import com.example.criticaltechworkstaskapp.common.showSnackbar
import com.example.criticaltechworkstaskapp.common.showToast
import com.example.criticaltechworkstaskapp.databinding.FragmentTopHeadLinesBinding
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.presentation.adapter.NewsAdapter
import com.example.criticaltechworkstaskapp.presentation.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

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
        _binding = FragmentTopHeadLinesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews(checkFlavourReturnNewsSource(), BuildConfig.API_KEY)
        loadSettings()
        showWelcomeMarqueeText()
        setUpMenu()
        observeState()
        newsAdapter = NewsAdapter{
            transitionToDetailView(it)
        }

    }

    private fun setUpMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_layout, menu)

                val menuItem = menu.findItem(R.id.search_news_title)
                val searchView = menuItem.actionView as SearchView


                searchView.setOnQueryTextListener(object : OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                       return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText != null) {
                            filterByNewsTitle(newText)
                        }
                        return true
                    }

                })

            }


            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.settings -> {
                        findNavController().navigate(R.id.action_topHeadLinesFragment_to_settingsFragment2)
                        return true
                    }
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
            errorText.isVisible = false
            newsAdapter.submitList(news)
            rvList.layoutManager = LinearLayoutManager(requireContext())
            rvList.adapter = newsAdapter
            val decoration = DividerItemDecoration(requireContext(), LinearLayout.VERTICAL)
            rvList.addItemDecoration(decoration)

            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.visibility = View.GONE

        }
    }

    private fun filterByNewsTitle(text: String) {

        val filteredlist: ArrayList<News> = ArrayList()

        viewModel.newsFromNetwork.observe(viewLifecycleOwner) { allNews->
            for (item in allNews?.data.orEmpty()) {
                if (item.title?.lowercase(Locale.getDefault())?.contains(text.lowercase(Locale.getDefault())) == true) {
                    filteredlist.add(item)
                }
            }
        }

        if (filteredlist.isEmpty()) {
            requireContext().showToast("No News Title Found..")
        } else {
            newsAdapter.submitList(filteredlist)
        }
    }


    private fun transitionToDetailView(news: News) {

        val action = TopHeadLinesFragmentDirections.actionTopHeadLinesFragmentToHeadLineDetailsFragment(news)
        Navigation.findNavController(requireView()).navigate(action)

    }


    private fun loadSettings() {
        val sp = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val nightMode = sp.getBoolean("nightorday", false)

        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

    }

    private fun checkFlavourReturnNewsSource():String{
        return  if(BuildConfig.FLAVOR.equals(Constants.REUTERS_FLAVOUR)) {
            Constants.REUTERS_FLAVOUR_NEWS_SOURCE
        } else if (BuildConfig.FLAVOR.equals(Constants.GOOGLE_FLAVOUR)) {
            Constants.GOOGLE_FLAVOUR_NEWS_SOURCE
        }else if (BuildConfig.FLAVOR.equals(Constants.CNN_FLAVOUR)){
            Constants.CNN_FLAVOUR_NEWS_SOURCE
        }else Constants.MAIN_APP_NEWS_SOURCE
    }

}