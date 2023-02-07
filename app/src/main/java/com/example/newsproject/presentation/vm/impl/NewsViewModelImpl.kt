package com.example.newsproject.presentation.vm.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.domain.usecase.IBreakingNewsUseCase
import com.example.newsproject.domain.usecase.ITopStoriesUseCase
import com.example.newsproject.model.NewsModel
import com.example.newsproject.presentation.vm.INewsViewModel
import com.example.newsproject.util.ResultEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModelImpl constructor(
    private val topStoriesUseCase: ITopStoriesUseCase,
    private val breakingNewsUseCaseImpl: IBreakingNewsUseCase
) : ViewModel(), INewsViewModel {

    private val _newsStateFlow = MutableStateFlow<ResultEvent<List<NewsModel>>>(ResultEvent.Loading)
    override val newsStateFlow: StateFlow<ResultEvent<List<NewsModel>>>
        get() = _newsStateFlow.asStateFlow()

    init {
        getTopStories()
    }

    fun getTopStories() {
        viewModelScope.launch {
            _newsStateFlow.emit(topStoriesUseCase.invoke())
            _newsStateFlow.emit(breakingNewsUseCaseImpl.invoke())
        }
    }
}