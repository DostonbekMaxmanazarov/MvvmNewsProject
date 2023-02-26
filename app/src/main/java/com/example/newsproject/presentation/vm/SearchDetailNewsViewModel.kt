package com.example.newsproject.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsproject.domain.usecase.ISearchingAddBookmarkUseCase
import com.example.newsproject.model.SearchNewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchDetailNewsViewModel @Inject constructor(
    private val searchingAddBookmarkUseCase: ISearchingAddBookmarkUseCase
) : ViewModel() {

    fun addBookmarkNews(searchingNewsItemModel: SearchNewsModel) {
        viewModelScope.launch {
            searchingAddBookmarkUseCase(searchingNewsItemModel)
        }
    }
}