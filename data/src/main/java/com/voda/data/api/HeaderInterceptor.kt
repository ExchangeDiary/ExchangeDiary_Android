package com.voda.data.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request()
                    .newBuilder()
//                    .addHeader("x-api-key", BuildConfig.API_KEY)
        return chain.proceed(builder.build())
    }
}
