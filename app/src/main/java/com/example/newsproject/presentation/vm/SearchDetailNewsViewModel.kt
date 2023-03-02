package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.model.SearchNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailNewsViewModel @Inject constructor(
    private val searchingAddBookmarkUseCase: ISearchingAddBookmarkUseCase
) : ViewModel() {

    private val _bookmarkSharedFlow =
        MutableSharedFlow<ResultEvent<Boolean>>()

    val bookmarkSharedFlow: SharedFlow<ResultEvent<Boolean>>
        get() = _bookmarkSharedFlow.asSharedFlow()

    fun addBookmarkNews(searchingNewsItemModel: SearchNewsModel) {
        viewModelScope.launch {
            _bookmarkSharedFlow.emit(searchingAddBookmarkUseCase(searchingNewsItemModel))
        }
    }
}