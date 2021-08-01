package com.voda.data.di

import com.voda.data.BuildConfig
import com.voda.data.api.HeaderInterceptor
import com.voda.data.api.diary.DiaryApiService
import com.voda.data.api.helper.NetworkResponseAdapterFactory
import com.voda.data.api.nullOnEmptyConverterFactory
import com.voda.data.source.diary.DefaultDiaryRepository
import com.voda.data.source.diary.remote.DiaryRemote
import com.voda.data.source.diary.remote.DiaryRemoteDataSource
import com.voda.domain.repository.DiaryRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private fun provideInterceptor(): Interceptor {
    return HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

private fun provideHeaderInterceptor(): HeaderInterceptor {
    return HeaderInterceptor()
}

private fun provideOkHttpClient(interceptor: Interceptor, headerInterceptor: HeaderInterceptor): OkHttpClient {
    return OkHttpClient.Builder().apply {
        this.connectTimeout(5, TimeUnit.SECONDS)
        this.readTimeout(5, TimeUnit.SECONDS)
        this.writeTimeout(5, TimeUnit.SECONDS)
//        this.addInterceptor(headerInterceptor)
        if (BuildConfig.DEBUG) this.addInterceptor(interceptor)
    }.build()
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(client)
//        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(nullOnEmptyConverterFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(NetworkResponseAdapterFactory())
        .build()
}

private fun provideDiaryApiService(retrofit: Retrofit): DiaryApiService {
    return retrofit.create(DiaryApiService::class.java)
}

val dataModule = module {

    // interceptor
    factory { provideInterceptor() }
    factory { provideHeaderInterceptor() }

    // okHttp
    factory (named("OkHttpClient")) { provideOkHttpClient(get(), get()) }

    // retrofit
    factory(named("Retrofit")) { provideRetrofit(get(named("OkHttpClient")) ) }

    // api service
    single { provideDiaryApiService(get(named("Retrofit"))) }

    // data source
    single(named("DiaryRemoteDataSource")) { DiaryRemoteDataSource(get()) } bind DiaryRemote::class

    // repo
    single { DefaultDiaryRepository(get()) } bind DiaryRepository::class

}