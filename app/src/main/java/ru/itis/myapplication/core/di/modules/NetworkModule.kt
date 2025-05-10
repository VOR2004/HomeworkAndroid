package ru.itis.myapplication.core.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.myapplication.BuildConfig
import ru.itis.myapplication.features.weatherlist.data.remote.api.WeatherApi
import ru.itis.myapplication.core.network.interceptors.AppIdInterceptor

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