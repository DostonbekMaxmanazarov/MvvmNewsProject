package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.ISearchNewsUseCase
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.model.SearchNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerticalSearchNewsViewModel @Inject constructor(
    private val searchNewsUseCaseImpl: ISearchNewsUseCase,
    private val searchAddBookmarkUseCase: ISearchingAddBookmarkUseCase
) : ViewModel() {

    private val _newsStateFlow =
        MutableStateFlow<ResultEvent<List<SearchNewsModel>>>(ResultEvent.Loading(true))

    val newsStateFlow: StateFlow<ResultEvent<List<SearchNewsModel>>>
        get() = _newsStateFlow.asStateFlow()

    private val _bookmarkSharedFlow =
        MutableSharedFlow<ResultEvent<Boolean>>()

    val bookmarkSharedFlow: SharedFlow<ResultEvent<Boolean>>
        get() = _bookmarkSharedFlow.asSharedFlow()

    fun getSearchNews(searchText: String) {
        viewModelScope.launch {
            _newsStateFlow.emit(ResultEvent.Loading(true))
            delay(300)
            _newsStateFlow.emit(searchNewsUseCaseImpl.invoke(searchText.ifEmpty { "All" }))
            delay(300)
            _newsStateFlow.emit(ResultEvent.Loading(false))
        }
    }

    fun addBookmarkNews(searchNewsItemModel: SearchNewsModel) {
        viewModelScope.launch {
            _bookmarkSharedFlow.emit(searchAddBookmarkUseCase(searchNewsItemModel))
        }
    }
}