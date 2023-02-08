package com.example.newsproject.presentation.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.databinding.ItemBreakingNewsBinding
import com.example.newsproject.databinding.ItemBreakingNewsTitleBinding
import com.example.newsproject.databinding.ItemChildNewsBinding
import com.example.newsproject.databinding.ItemNewsTopStoriesBinding
import com.example.newsproject.databinding.ItemNewsTopStoriesTitleBinding
import com.example.newsproject.model.BreakingNewsModel
import com.example.newsproject.model.BreakingNewsTitleModel
import com.example.newsproject.model.TopStoriesNewsModel
import com.example.newsproject.model.TopStoriesNewsTitleModel

sealed class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class BreakingNewsTitleVH(private val binding: ItemBreakingNewsTitleBinding) :
        BaseVH(binding.root) {
        fun bind(data: BreakingNewsTitleModel) {
            binding.tvDate.text = data.title
            binding.tvTitle.text = data.strDate
        }
    }

    class BreakingNewsVH(binding: ItemChildNewsBinding, mContext: Context) : BaseVH(binding.root) {
        private var data = BreakingNewsModel(emptyList())
        private var adapter: NewsChildAdapter? = null

        init {
            adapter = NewsChildAdapter()
            binding.rv.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            binding.rv.setHasFixedSize(true)
            binding.rv.adapter = adapter

        }

        fun setData(breakingNewsModel: BreakingNewsModel) {
            adapter?.submitList(breakingNewsModel.news.toMutableList())
        }
    }

    class NewsTopStoriesVH(private val binding: ItemNewsTopStoriesBinding) : BaseVH(binding.root) {
        fun bind(data: TopStoriesNewsModel) {
            binding.tvTitle.text = data.name
            binding.tvDescription.text = data.content
        }
    }

    class NewsTopStoriesTitleVH(private val binding: ItemNewsTopStoriesTitleBinding) :
        BaseVH(binding.root) {
        fun bind(data: TopStoriesNewsTitleModel) {
            binding.tvTitle.text = data.title
        }
    }
}