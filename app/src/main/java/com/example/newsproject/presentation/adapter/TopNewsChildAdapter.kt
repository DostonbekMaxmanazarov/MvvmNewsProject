package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemNewsTopStoriesBinding
import com.example.newsproject.model.BaseNewsModel
import com.example.newsproject.model.TopStoriesNewsItemModel
import com.example.newsproject.model.TopStoriesNewsTitleModel

@SuppressLint("NotifyDataSetChanged")
class TopNewsChildAdapter : RecyclerView.Adapter<TopNewsChildAdapter.VH>() {

    private val mList = mutableListOf<TopStoriesNewsItemModel>()
    private lateinit var binding: ItemNewsTopStoriesBinding
    private var listener: ((TopStoriesNewsItemModel) -> Unit)? = null

    fun setOnClickListener(listener: (TopStoriesNewsItemModel) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemNewsTopStoriesBinding.inflate(view, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<TopStoriesNewsItemModel>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(
        private val binding: ItemNewsTopStoriesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener?.invoke(mList[adapterPosition])
            }
        }

        fun bind(data: TopStoriesNewsItemModel) {
            binding.tvTitle.text = data.name
            binding.tvDescription.text = data.content
            binding.tvDate.text = data.publishedAt

            Glide.with(binding.root).load(data.imageUrl).placeholder(R.drawable.nature_photo)
                .into(binding.ivNews)
        }
    }
}