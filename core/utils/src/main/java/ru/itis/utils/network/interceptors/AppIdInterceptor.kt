package ru.itis.utils.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import ru.itis.utils.BuildConfig
import javax.inject.Inject

class AppIdInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter(BuildConfig.APP_ID_NAME, BuildConfig.WEATHER_API_KEY)
            .build()
        val request = chain.request().newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}