package com.example.newsproject.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.newsproject.R
import com.example.newsproject.databinding.ItemTabCategoryBinding
import com.example.newsproject.model.CategoriesTabModel

@SuppressLint("NotifyDataSetChanged")
class CategoryTabAdapter : RecyclerView.Adapter<CategoryTabAdapter.VH>() {

    private lateinit var binding: ItemTabCategoryBinding

    private val mList = mutableListOf<CategoriesTabModel>()
    private var selectedPosition = -1

    private var listener: ((CategoriesTabModel) -> Unit)? = null

    fun setOnClickListener(listener: (CategoriesTabModel) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
        binding = ItemTabCategoryBinding.inflate(view, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(mList[position])

    override fun getItemCount() = mList.size

    fun updateData(position: Int) {
        mList.map {
            it.isSelected = false
        }
        mList[position].isSelected = true
        selectedPosition = position

    }

    fun submitList(data: MutableList<CategoriesTabModel>) {
        mList.clear()
        mList.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(
        private val binding: ItemTabCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val data = mList[adapterPosition]

                if (selectedPosition != -1) {
                    mList[selectedPosition].isSelected = false
                    Log.d("Selection ", "selectedPosition != -1: $selectedPosition")
                    notifyItemChanged(selectedPosition)
                }
                data.isSelected = true
                selectedPosition = adapterPosition
                notifyItemChanged(adapterPosition)
                Log.d("Selection ", "selectedPosition = adapterPosition: $selectedPosition")

                listener?.invoke(mList[adapterPosition])
            }
        }

        fun bind(data: CategoriesTabModel) {
            binding.tvTitle.text = data.title
            if (data.isSelected) {
                binding.tvTitle.setBackgroundResource(R.drawable.bg_white_shape_text_view)
                binding.tvTitle.setTextColor(android.graphics.Color.parseColor("#000000"))
            } else {
                binding.tvTitle.setBackgroundResource(R.drawable.bg_shape_text_view)
                binding.tvTitle.setTextColor(android.graphics.Color.parseColor("#FFFFFFFF"))
            }
        }
    }
}