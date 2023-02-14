package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentDetailBinding
import com.example.newsproject.model.BaseNewsItemModel
import com.example.newsproject.model.BreakingNewsItemModel
import com.example.newsproject.model.TopStoriesNewsItemModel
import com.example.newsproject.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var data: BaseNewsItemModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        data = requireArguments().getParcelable(Constants.SENDING_NEWS_MODEL)
        loadData()
    }

    private fun loadData() {
        if (data is TopStoriesNewsItemModel) {
            (data as TopStoriesNewsItemModel).apply {
                binding.tvDate.text = publishedAt
                binding.tvTitle.text = name
                binding.tvDescription.text = content

                Glide.with(binding.root).load(imageUrl).placeholder(R.drawable.nature_photo)
                    .into(binding.ivNews)
            }
        } else if (data is BreakingNewsItemModel) {
            (data as BreakingNewsItemModel).apply {
                binding.tvDate.text = publishedAt
                binding.tvTitle.text = name
                binding.tvDescription.text = content

                Glide.with(binding.root).load(imageUrl).placeholder(R.drawable.nature_photo)
                    .into(binding.ivNews)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}