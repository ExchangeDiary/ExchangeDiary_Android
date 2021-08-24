package com.voda.domain.di

import com.voda.domain.usecase.diary.GetDiaryUseCase
import com.voda.domain.usecase.home.GetHomeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetDiaryUseCase(get()) }
    factory { GetHomeUseCase(get()) }

}