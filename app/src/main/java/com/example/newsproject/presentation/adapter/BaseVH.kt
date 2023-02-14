package com.example.newsproject.presentation.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.databinding.ItemBreakingNewsTitleBinding
import com.example.newsproject.databinding.ItemChildBreakingNewsBinding
import com.example.newsproject.databinding.ItemChildTopNewsBinding
import com.example.newsproject.databinding.ItemNewsTopStoriesTitleBinding
import com.example.newsproject.model.*

sealed class BaseVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    class BreakingNewsTitleVH(private val binding: ItemBreakingNewsTitleBinding) :
        BaseVH(binding.root) {
        fun bind(data: BreakingNewsTitleModel) {
            binding.tvDate.text = data.strDate
            binding.tvTitle.text = data.title
        }
    }

    class BreakingNewsVH(
        binding: ItemChildBreakingNewsBinding,
        mContext: Context,
        listener: (BaseNewsItemModel) -> Unit
    ) : BaseVH(binding.root) {
        private var adapter: BreakingNewsChildAdapter? = null

        init {
            adapter = BreakingNewsChildAdapter()
            binding.rv.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            binding.rv.setHasFixedSize(true)
            binding.rv.adapter = adapter
            adapter?.setOnClickListener { listener.invoke(it) }
        }

        fun setData(breakingNewsModel: BreakingNewsModel) {
            adapter?.submitList(breakingNewsModel.breakingNews.toMutableList())
        }
    }

    class NewsTopStoriesVH(
        binding: ItemChildTopNewsBinding, mContext: Context, listener: (BaseNewsItemModel) -> Unit
    ) : BaseVH(binding.root) {
        private var adapter: TopNewsChildAdapter? = null

        init {
            adapter = TopNewsChildAdapter()
            binding.rv.layoutManager =
                LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            binding.rv.setHasFixedSize(true)
            binding.rv.adapter = adapter
            adapter?.setOnClickListener { listener.invoke(it) }
        }

        fun setData(breakingNewsModel: TopStoriesNewsModel) {
            adapter?.submitList(breakingNewsModel.topNews.toMutableList())
        }
    }

    class NewsTopStoriesTitleVH(private val binding: ItemNewsTopStoriesTitleBinding) :
        BaseVH(binding.root) {
        fun bind(data: TopStoriesNewsTitleModel) {
            binding.tvTitle.text = data.title
        }
    }
}