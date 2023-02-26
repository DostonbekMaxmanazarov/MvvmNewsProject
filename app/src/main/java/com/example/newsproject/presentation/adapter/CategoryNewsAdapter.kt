package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemCategoryNewsBinding
import com.example.newsproject.model.CategoryNewsModel

@SuppressLint("NotifyDataSetChanged")
class CategoryNewsAdapter : RecyclerView.Adapter<CategoryNewsAdapter.VH>() {

    private lateinit var binding: ItemCategoryNewsBinding

    private val mList = mutableListOf<CategoryNewsModel>()

    private var listener: ((CategoryNewsModel) -> Unit)? = null

    fun setOnClickListener(listener: (CategoryNewsModel) -> Unit) {
        this.listener = listener
    }

    private var bookmarkListener: ((CategoryNewsModel) -> Unit)? = null

    fun setOnClickBookmarkListener(listener: (CategoryNewsModel) -> Unit) {
        this.bookmarkListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemCategoryNewsBinding.inflate(view, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<CategoryNewsModel>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(
        private val binding: ItemCategoryNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener?.invoke(mList[adapterPosition])
            }

            binding.ivBookMark.setOnClickListener {
                bookmarkListener?.invoke(mList[adapterPosition])
                binding.ivBookMark.setImageResource(R.drawable.ic_bookmark_selected)
            }
        }

        fun bind(data: CategoryNewsModel) {
            binding.tvName.text = data.name
            binding.tvTitle.text = data.title
            binding.tvDate.text = data.publishedAt

            if (data.imageUrl?.isNotEmpty() == true) Glide.with(binding.root).load(data.imageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .transition(DrawableTransitionOptions.withCrossFade()).into(binding.ivNewsPhoto)
            else binding.ivNewsPhoto.setImageResource(R.drawable.nature_photo)
        }
    }
}