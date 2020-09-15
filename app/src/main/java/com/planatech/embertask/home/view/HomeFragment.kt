package com.planatech.embertask.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.planatech.embertask.R
import com.planatech.embertask.databinding.FragmentHomeBinding
import com.planatech.embertask.home.viewmodel.HomeViewModel

class HomeFragment: Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var homeViewModel: HomeViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        loadViewModel()
        return binding?.root
    }

    private fun loadViewModel(){
        homeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        homeViewModel?.loadArticles()

        val articleAdapter = ArticleAdapter()

        homeViewModel?.articleList?.observe(viewLifecycleOwner, Observer {
            it.let{
                articleAdapter.submitList(it)
                articleAdapter.notifyDataSetChanged()
            }
        })
    }

}