package com.example.newsproject.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CategoryNewsModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SearchNewsModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CategoryNewsLocalModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ParseToCategoryNewsModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CategoryBookmarkLocalModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SearchingBookmarkLocalModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BookmarkModuleMapper