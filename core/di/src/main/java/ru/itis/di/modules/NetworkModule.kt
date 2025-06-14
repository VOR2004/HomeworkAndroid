package ru.itis.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.data.api.WeatherApi
import ru.itis.utils.BuildConfig
import ru.itis.utils.network.interceptors.AppIdInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideOkHttpClient(
        appIdInterceptor: AppIdInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        return okHttpClient.addInterceptor(appIdInterceptor).build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): WeatherApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_WEATHER)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
        return retrofit.create(WeatherApi::class.java)
    }
}