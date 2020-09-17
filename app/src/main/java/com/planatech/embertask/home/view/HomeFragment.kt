package com.planatech.embertask.home.view

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.planatech.embertask.R
import com.planatech.embertask.common.*
import com.planatech.embertask.databinding.FragmentHomeBinding
import com.planatech.embertask.home.model.Article
import com.planatech.embertask.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var homeViewModel: HomeViewModel? = null
    private var navController: NavController? = null
    private var articleAdapter: ArticleAdapter? = null
    private var articlesList: List<Article>? = null
    private var filterDialog: FilterDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        loadViewModel()
        navController = findNavController()
        setHasOptionsMenu(true)
        return binding?.root
    }

    private fun loadViewModel() {
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        homeViewModel?.loadArticles("us", null)
        homeViewModel?.loadSources()
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel?.articleList?.observe(viewLifecycleOwner, Observer {
            it.let {
                articleAdapter = createListAdapter(it)
                articlesList = it
                binding?.adapter = articleAdapter
            }
        })
        homeViewModel?.sourcesList?.observe(viewLifecycleOwner, Observer {
            filterDialog = FilterDialog(it) { country, source ->
                homeViewModel?.loadArticles(country, source)
            }
        })
    }

    private fun createListAdapter(it: List<Article>?): ArticleAdapter {
        val articleAdapter = ArticleAdapter { article ->
            val bundle = Bundle()
            bundle.putString(ARTICLE_AUTHOR, article.author)
            bundle.putString(ARTICLE_TITLE, article.title)
            bundle.putString(ARTICLE_CONTENT, article.content)
            bundle.putString(ARTICLE_IMAGE_URL, article.urlToImage)
            bundle.putString(ARTICLE_SOURCE_NAME, article.source.name)
            bundle.putString(ARTICLE_SOURCE_URL, article.url)
            navController?.navigate(R.id.action_homeFragment_to_articleDetailsFragment, bundle)
        }
        articleAdapter.submitList(it)
        articleAdapter.notifyDataSetChanged()
        return articleAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.filter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter_action -> {
                this.context?.let {
                    filterDialog?.show(parentFragmentManager, "FilterDialog")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}