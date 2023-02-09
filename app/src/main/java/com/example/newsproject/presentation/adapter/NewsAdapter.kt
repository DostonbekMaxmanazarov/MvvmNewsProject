package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.databinding.*
import com.example.newsproject.model.*

const val BREAKING_NEWS = R.layout.item_child_breaking_news
const val BREAKING_NEWS_TITLE = R.layout.item_breaking_news_title
const val NEWS_TOP_STORIES = R.layout.item_child_top_news
const val NEWS_TOP_STORIES_TITLE = R.layout.item_news_top_stories_title

@SuppressLint("NotifyDataSetChanged")
class NewsAdapter : RecyclerView.Adapter<BaseVH>() {

    private val mList = mutableListOf<BaseNewsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            BREAKING_NEWS -> BaseVH.BreakingNewsVH(ItemChildBreakingNewsBinding.bind(view), parent.context)
            BREAKING_NEWS_TITLE -> BaseVH.BreakingNewsTitleVH(ItemBreakingNewsTitleBinding.bind(view))
            NEWS_TOP_STORIES -> BaseVH.NewsTopStoriesVH(ItemChildTopNewsBinding.bind(view),parent.context)
            NEWS_TOP_STORIES_TITLE -> BaseVH.NewsTopStoriesTitleVH(
                ItemNewsTopStoriesTitleBinding.bind(
                    view
                )
            )
            else -> throw java.lang.IllegalArgumentException("wrong item type")
        }
    }

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        when (holder) {
            is BaseVH.BreakingNewsVH -> holder.setData(mList[position] as BreakingNewsModel)
            is BaseVH.BreakingNewsTitleVH -> holder.bind(mList[position] as BreakingNewsTitleModel)
            is BaseVH.NewsTopStoriesVH -> holder.setData(mList[position] as TopStoriesNewsModel)
            is BaseVH.NewsTopStoriesTitleVH -> holder.bind(mList[position] as TopStoriesNewsTitleModel)
        }
    }

    override fun getItemViewType(position: Int) = when (mList[position]) {
        is BreakingNewsModel -> BREAKING_NEWS
        is BreakingNewsTitleModel -> BREAKING_NEWS_TITLE
        is TopStoriesNewsTitleModel -> NEWS_TOP_STORIES_TITLE
        is TopStoriesNewsModel -> NEWS_TOP_STORIES
        else -> throw IllegalArgumentException("wrong item type")
    }

    override fun getItemCount() = mList.size

    fun submitData(data: BaseNewsModel) {
        mList.add(data)
        notifyDataSetChanged()
    }
}