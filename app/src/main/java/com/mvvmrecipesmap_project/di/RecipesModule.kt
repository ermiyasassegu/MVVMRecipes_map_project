package com.mvvmrecipesmap_project.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mvvmrecipesmap_project.data.remote.MealApi
import com.mvvmrecipesmap_project.data.repository.MealRepositoryImpl
import com.mvvmrecipesmap_project.domain.repository.MealsRepository
import com.mvvmrecipesmap_project.util.Constants

@Module
@InstallIn(SingletonComponent::class)
object RecipesModule {

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    fun providesRetrofitInstance(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): MealApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(MealApi::class.java)
    }

    @Provides
    fun providesMealRepository(mealApi: MealApi): MealsRepository {
        return MealRepositoryImpl(mealApi)
    }
}