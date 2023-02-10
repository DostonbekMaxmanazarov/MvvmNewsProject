package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentNewsBinding
import com.example.newsproject.presentation.adapter.NewsAdapter
import com.example.newsproject.presentation.vm.NewsViewModel
import com.example.newsproject.datasource.utils.ResultEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private val vm by viewModels<NewsViewModel>()

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentNewsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter()
        binding.apply {
            rv.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rv.adapter = adapter
        }
    }

    private fun loadData() {
        vm.newsStateFlow.onEach {
            when (it) {
                is ResultEvent.Success -> adapter.submitData(data = it.data)
                is ResultEvent.Failure -> {
                    showDialog()
                    binding.rv.visibility = View.GONE
                }
                is ResultEvent.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext()).setTitle("Error")
            .setMessage("There is an error loading data from the server. Do you want to load data from a database?")
            .setPositiveButton(
                "Yes"
            ) { _, _ ->

            }.setNegativeButton(
                "No", null
            ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}