package com.planatech.embertask.article_details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.planatech.embertask.R
import com.planatech.embertask.article_details.viewmodel.ArticleDetailsViewModel
import com.planatech.embertask.article_details.viewmodel.ArticleDetailsViewModelFactory
import com.planatech.embertask.common.*
import com.planatech.embertask.databinding.FragmentArticleDetailsBinding


class ArticleDetailsFragment : Fragment() {

    private var articleAuthor: String? = null
    private var articleTitle: String? = null
    private var articleDescription: String? = null
    private var articleImageUrl: String? = null
    private var articleSourceName: String? = null
    private var articleSourceUrl: String? = null
    private var binding: FragmentArticleDetailsBinding? = null
    private var articleDetailsViewModel: ArticleDetailsViewModel? = null
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleAuthor = it.getString(ARTICLE_AUTHOR)
            articleTitle = it.getString(ARTICLE_TITLE)
            articleDescription = it.getString(ARTICLE_CONTENT)
            articleImageUrl = it.getString(ARTICLE_IMAGE_URL)
            articleSourceName = it.getString(ARTICLE_SOURCE_NAME)
            articleSourceUrl = it.getString(ARTICLE_SOURCE_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_article_details, container, false)
        setHasOptionsMenu(true)
        navController = findNavController()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        articleDetailsViewModel = ViewModelProvider(
            this,
            ArticleDetailsViewModelFactory(
                articleAuthor, articleTitle, articleDescription,
                articleImageUrl, articleSourceName, articleSourceUrl
            )
        ).get(ArticleDetailsViewModel::class.java)

        binding?.viewModel = articleDetailsViewModel

        handleActionBar()
    }

    private fun handleActionBar() {
        requireActivity().let {
            (it as AppCompatActivity).supportActionBar?.title =
                articleDetailsViewModel?.articleSourceName
            it.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                navController?.navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}