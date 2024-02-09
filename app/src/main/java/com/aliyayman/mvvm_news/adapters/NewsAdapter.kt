package com.aliyayman.mvvm_news.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aliyayman.mvvm_news.R
import com.aliyayman.mvvm_news.model.Article
import com.bumptech.glide.Glide

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_preview, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val tvSource = holder.itemView.findViewById<TextView>(R.id.tvSource)
        val tvTitle = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = holder.itemView.findViewById<TextView>(R.id.tvDescription)
        val tvPuslishedAt = holder.itemView.findViewById<TextView>(R.id.tvPublishedAt)
        val article = differ.currentList[position]
        val ivImageView = holder.itemView.findViewById<ImageView>(R.id.ivArticleImage)
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivImageView)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPuslishedAt.text = article.publishedAt
            setOnItemclickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemclickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}