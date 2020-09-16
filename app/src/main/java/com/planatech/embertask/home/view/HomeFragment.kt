package com.planatech.embertask.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.planatech.embertask.R
import com.planatech.embertask.common.*
import com.planatech.embertask.databinding.FragmentHomeBinding
import com.planatech.embertask.home.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var homeViewModel: HomeViewModel? = null
    private var navController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        loadViewModel()
        navController = findNavController()
        return binding?.root
    }

    private fun loadViewModel() {
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        homeViewModel?.loadArticles()

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

        homeViewModel?.articleList?.observe(viewLifecycleOwner, Observer {
            it.let {
                articleAdapter.submitList(it)
                articleAdapter.notifyDataSetChanged()
                binding?.adapter = articleAdapter
            }
        })
    }

}