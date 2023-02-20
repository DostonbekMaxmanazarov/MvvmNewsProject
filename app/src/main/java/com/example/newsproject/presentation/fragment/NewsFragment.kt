package com.example.newsproject.presentation.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentNewsBinding
import com.example.newsproject.presentation.adapter.NewsAdapter
import com.example.newsproject.presentation.vm.NewsViewModel
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.model.CategoryNewsModel
import com.example.newsproject.model.BreakingNewsTitleModel
import com.example.newsproject.model.TopStoriesNewsModel
import com.example.newsproject.model.TopStoriesNewsTitleModel
import com.example.newsproject.util.Constants
import com.example.newsproject.util.addFragment
import com.example.newsproject.util.toDateFormatted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Date

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
        initView()
        initClickView()
    }

    private fun initView() {
        binding.swipeRefresh.setOnRefreshListener {
            vm.getTopStories()
        }
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
                is ResultEvent.Success -> {
                    binding.rv.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = false
                    if (it.data is CategoryNewsModel) {
                        adapter.submitData(
                            BreakingNewsTitleModel(
                                "Breaking news", Date().toDateFormatted()
                            )
                        )
                        adapter.submitData(data = it.data)
                    } else if (it.data is TopStoriesNewsModel) {
                        adapter.submitData(TopStoriesNewsTitleModel("Top Stories"))
                        adapter.submitData(data = it.data)
                    }
                }
                is ResultEvent.Failure -> {
                    showDialog()
                    binding.rv.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                }
                is ResultEvent.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    binding.swipeRefresh.isRefreshing = false
                }
                is ResultEvent.Loading -> {
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initClickView() {
        adapter.setOnClickListener {
            addFragment(
                containerId = R.id.container,
                fragment = DetailFragment(),
                args = bundleOf(Constants.SENDING_NEWS_MODEL to it),
                addToBackStack = true
            )
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage(
            "There is an error loading data from the server. " + "Do you want to load data from a database?"
        ).setPositiveButton(
            "Yes"
        ) { _, _ ->
            vm.getTopStories(isLoadingLocal = true)
        }.setNegativeButton(
            "No", null
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}