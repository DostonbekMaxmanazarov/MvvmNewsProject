package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.ISearchNewsUseCase
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.model.SearchNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val searchNewsUseCaseImpl: ISearchNewsUseCase,
    private val searchAddBookmarkUseCase: ISearchingAddBookmarkUseCase
) : ViewModel() {

    private val _newsStateFlow =
        MutableStateFlow<ResultEvent<List<SearchNewsModel>>>(ResultEvent.Success(emptyList()))

    val newsStateFlow: StateFlow<ResultEvent<List<SearchNewsModel>>>
        get() = _newsStateFlow.asStateFlow()

    private val _bookmarkStateFlow =
        MutableStateFlow<ResultEvent<Boolean>>(ResultEvent.Success(false))

    val bookmarkStateFlow: StateFlow<ResultEvent<Boolean>>
        get() = _bookmarkStateFlow.asStateFlow()


    fun getTopStories(searchText: String = "All") {

        viewModelScope.launch {
            _newsStateFlow.emit(ResultEvent.Loading(true))
            _newsStateFlow.emit(searchNewsUseCaseImpl(searchText.ifEmpty { "All" }))
            _newsStateFlow.emit(ResultEvent.Loading(false))

        }
    }

    fun addBookmarkNews(searchNewsItemModel: SearchNewsModel) {
        viewModelScope.launch {
            _bookmarkStateFlow.emit(searchAddBookmarkUseCase(searchNewsItemModel))
        }
    }
}