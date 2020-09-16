package com.planatech.embertask.home.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.planatech.embertask.databinding.HeadlineListItemBinding
import com.planatech.embertask.home.model.Article

class ArticleAdapter(val itemClickCallback: (article: Article) -> Unit) :
    ListAdapter<Article, ArticleAdapter.ArticleViewHolder>(Companion) {

    class ArticleViewHolder(val binding: HeadlineListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
            oldItem.title == newItem.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HeadlineListItemBinding.inflate(layoutInflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = getItem(position)
        holder.binding.article = currentArticle
        holder.binding.executePendingBindings()
        holder.itemView.setOnClickListener {
            itemClickCallback(currentArticle)
        }
    }
}