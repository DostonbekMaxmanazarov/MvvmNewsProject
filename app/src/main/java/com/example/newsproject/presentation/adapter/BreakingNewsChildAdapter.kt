package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemBreakingNewsBinding
import com.example.newsproject.model.BaseNewsItemModel
import com.example.newsproject.model.CategoryNewsItemModel

@SuppressLint("NotifyDataSetChanged")
class BreakingNewsChildAdapter : RecyclerView.Adapter<BreakingNewsChildAdapter.VH>() {

    private val mList = mutableListOf<CategoryNewsItemModel>()
    private lateinit var binding: ItemBreakingNewsBinding
    private var listener: ((BaseNewsItemModel) -> Unit)? = null

    fun setOnClickListener(listener: (BaseNewsItemModel) -> Unit) {
        this.listener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemBreakingNewsBinding.inflate(view, parent, false)
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
        private val binding: ItemBreakingNewsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

       init {
           binding.root.setOnClickListener {
               listener?.invoke(mList[adapterPosition])
           }
       }

        fun bind(data: CategoryNewsItemModel) {
            binding.tvTitle.text = data.name
            binding.tvDescription.text = data.content
            binding.tvDate.text = data.publishedAt

            Glide.with(binding.root).load(data.imageUrl).placeholder(R.drawable.nature_photo)
                .into(binding.ivNews)
        }
    }
}