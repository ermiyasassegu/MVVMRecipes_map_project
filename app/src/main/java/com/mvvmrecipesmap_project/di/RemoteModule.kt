package com.mvvmrecipesmap_project.di

import android.content.Context
import com.google.gson.Gson
import com.mvvmrecipesmap_project.BuildConfig
import com.mvvmrecipesmap_project.map.network.service.PlacesService
import com.mvvmrecipesmap_project.map.network.util.AppCallAdapterFactory
import com.mvvmrecipesmap_project.map.util.Constant.NETWORK_REQUEST_TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun providesService(retrofit: Retrofit): PlacesService =
        retrofit.create(PlacesService::class.java)

    @Provides
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        appCallAdapterFactory: AppCallAdapterFactory,
    ): Retrofit {
        val interceptorDebug = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptorDebug.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(NETWORK_REQUEST_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_REQUEST_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(interceptorDebug)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.FOURSQUARE_BASE_URL)
            .addCallAdapterFactory(appCallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    @Provides
    fun providesOkhttpCache(@ApplicationContext context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(context.cacheDir, cacheSize.toLong())
    }


    @Provides
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun providesAppCallAdapterFactory(): AppCallAdapterFactory {
        return AppCallAdapterFactory()
    }
}