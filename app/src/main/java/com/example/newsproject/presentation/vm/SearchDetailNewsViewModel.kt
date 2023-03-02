package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.datasource.utils.ResultEvent
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.model.SearchNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailNewsViewModel @Inject constructor(
    private val searchingAddBookmarkUseCase: ISearchingAddBookmarkUseCase
) : ViewModel() {

    private val _bookmarkStateFlow =
        MutableStateFlow<ResultEvent<Boolean>>(ResultEvent.Success(false))

    val bookmarkStateFlow: StateFlow<ResultEvent<Boolean>>
        get() = _bookmarkStateFlow.asStateFlow()

    fun addBookmarkNews(searchingNewsItemModel: SearchNewsModel) {
        viewModelScope.launch {
            _bookmarkStateFlow.emit(searchingAddBookmarkUseCase(searchingNewsItemModel))
        }
    }
}