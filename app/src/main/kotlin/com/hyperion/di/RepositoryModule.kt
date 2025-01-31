package com.hyperion.di

import com.zt.innertube.domain.repository.InnerTubeRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::InnerTubeRepository)
}