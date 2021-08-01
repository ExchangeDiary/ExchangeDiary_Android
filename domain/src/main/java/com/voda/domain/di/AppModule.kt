package com.voda.domain.di

import com.voda.domain.usecase.diary.GetDiaryUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetDiaryUseCase(get()) }

}