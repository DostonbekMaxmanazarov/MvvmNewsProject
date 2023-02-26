package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemSearchNewsBinding
import com.example.newsproject.model.SearchNewsModel

@SuppressLint("NotifyDataSetChanged")
class SearchNewsAdapter : RecyclerView.Adapter<SearchNewsAdapter.VH>() {

    private lateinit var binding: ItemSearchNewsBinding

    private val mList = mutableListOf<SearchNewsModel>()

    private var listener: ((SearchNewsModel) -> Unit)? = null

    fun setOnClickListener(listener: (SearchNewsModel) -> Unit) {
        this.listener = listener
    }

    private var bookmarkListener: ((SearchNewsModel) -> Unit)? = null

    fun setOnClickBookmarkListener(listener: (SearchNewsModel) -> Unit) {
        this.bookmarkListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemSearchNewsBinding.inflate(view, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<SearchNewsModel>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(
        private val binding: ItemSearchNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener?.invoke(mList[adapterPosition])
            }

            binding.ivBookmark.setOnClickListener {
                bookmarkListener?.invoke(mList[adapterPosition])
            }
        }

        fun bind(data: SearchNewsModel) {
            binding.tvName.text = data.name
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.publishedAt

            Glide.with(binding.root).load(data.imageUrl).placeholder(R.drawable.ic_place_holder)
                .transition(DrawableTransitionOptions.withCrossFade()).into(binding.ivNews)
        }
    }
}