package com.hyperion.di

import androidx.paging.PagingConfig
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    fun providePagingConfig() = PagingConfig(
        pageSize = 20,
        enablePlaceholders = false
    )

    singleOf(::providePagingConfig)
}