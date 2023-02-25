package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemVerticalNewsBinding
import com.example.newsproject.model.CategoryNewsItemModel

@SuppressLint("NotifyDataSetChanged")
class VerticalNewsAdapter : RecyclerView.Adapter<VerticalNewsAdapter.VH>() {

    private lateinit var binding: ItemVerticalNewsBinding

    private val mList = mutableListOf<CategoryNewsItemModel>()

    private var listener: ((CategoryNewsItemModel) -> Unit)? = null

    fun setOnClickListener(listener: (CategoryNewsItemModel) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemVerticalNewsBinding.inflate(view, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<CategoryNewsItemModel>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(
        private val binding: ItemVerticalNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener?.invoke(mList[adapterPosition])
            }
        }

        fun bind(data: CategoryNewsItemModel) {
            binding.tvNewsName.text = data.name
            binding.tvNewsTitle.text = data.description
            binding.tvNewsDate.text = data.publishedAt

            if (data.imageUrl?.isNotEmpty() == true) Glide.with(binding.root).load(data.imageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .transition(DrawableTransitionOptions.withCrossFade()).into(binding.ivNewsPhoto)
            else binding.ivNewsPhoto.setImageResource(R.drawable.nature_photo)

        }
    }
}