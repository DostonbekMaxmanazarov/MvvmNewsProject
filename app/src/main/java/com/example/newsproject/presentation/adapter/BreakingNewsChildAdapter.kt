package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemBreakingNewsBinding
import com.example.newsproject.model.BreakingNewsItemModel

@SuppressLint("NotifyDataSetChanged")
class BreakingNewsChildAdapter : RecyclerView.Adapter<BreakingNewsChildAdapter.VH>() {

    private val mList = mutableListOf<BreakingNewsItemModel>()
    private lateinit var binding: ItemBreakingNewsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemBreakingNewsBinding.inflate(view, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun submitList(data: MutableList<BreakingNewsItemModel>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    class VH(
        private val binding: ItemBreakingNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: BreakingNewsItemModel) {
            binding.tvTitle.text = data.name
            binding.tvDescription.text = data.content
            binding.tvDate.text = data.publishedAt

            Glide.with(binding.root).load(data.imageUrl).placeholder(R.drawable.nature_photo)
                .into(binding.ivNews)
        }
    }
}