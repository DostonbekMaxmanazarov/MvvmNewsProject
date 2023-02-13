package com.example.newsproject.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BreakingNewsModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TopNewsModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BreakingNewsLocalModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TopNewsLocalModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ParseToBreakingNewsModuleMapper

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ParseToTopNewsModuleMapper