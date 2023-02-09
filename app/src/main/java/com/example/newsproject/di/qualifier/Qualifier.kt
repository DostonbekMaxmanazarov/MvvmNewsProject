package com.example.newsproject.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BreakingNewsModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TopNewsModuleMapper