package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemBookmarkNewsBinding
import com.example.newsproject.databinding.ItemCategoryNewsBinding
import com.example.newsproject.model.BookmarkNewsModel
import com.example.newsproject.model.CategoryNewsModel

@SuppressLint("NotifyDataSetChanged")
class BookmarkNewsAdapter : RecyclerView.Adapter<BookmarkNewsAdapter.VH>() {

    private lateinit var binding: ItemBookmarkNewsBinding

    private val mList = mutableListOf<BookmarkNewsModel>()

    private var listener: ((BookmarkNewsModel) -> Unit)? = null

    fun setOnClickListener(listener: (BookmarkNewsModel) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemBookmarkNewsBinding.inflate(view, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<BookmarkNewsModel>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(
        private val binding: ItemBookmarkNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener?.invoke(mList[adapterPosition])
            }
        }

        fun bind(data: BookmarkNewsModel) {
            binding.tvName.text = data.name
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.publishedAt

            if (data.imageUrl?.isNotEmpty() == true) Glide.with(binding.root).load(data.imageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .transition(DrawableTransitionOptions.withCrossFade()).into(binding.ivNews)
            else binding.ivNews.setImageResource(R.drawable.nature_photo)
        }
    }
}