package com.voda.presentation

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.voda.data.di.dataModule
import com.voda.domain.di.domainModule
import com.voda.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
        KakaoSdk.init(this, KAKAO_NATIVE_KEY)
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                mutableListOf(
                    dataModule,
                    domainModule,
                    presentationModule
                )
            )
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object{
        const val KAKAO_NATIVE_KEY = "59b3edad99f26e7b2a5cf6e890b5760c"
    }
}