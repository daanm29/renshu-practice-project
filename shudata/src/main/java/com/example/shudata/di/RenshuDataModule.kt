package com.example.shudata.di

import android.content.Context
import com.example.shudata.BuildConfig
import com.example.shudata.database.RenshuDatabase
import com.example.shudata.database.dao.KanaDao
import com.example.shudata.di.RenshuDataModule.Bindings
import com.example.shudata.kana.data.KanaService
import com.example.shudata.kana.data.LocalGetKanaRepository
import com.example.shudata.kana.data.RemoteGetKanaRepository
import com.example.shudomain.practice.repository.GetKanaRepository
import com.example.shudomain.practice.repository.GetLocalKanaRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(
    includes = [
        Bindings::class
    ]
)
class RenshuDataModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideKanaService(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): KanaService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.JAP_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(KanaService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): RenshuDatabase {
        return RenshuDatabase.getInstance(context)
    }

    @Provides
    fun provideKanaDao(database: RenshuDatabase): KanaDao = database.kanaDao()

    @Module
    interface Bindings {

        @Binds
        fun bindKanaRepository(repository: RemoteGetKanaRepository): GetKanaRepository

        @Binds
        fun bindLocalKanaRepository(repository: LocalGetKanaRepository): GetLocalKanaRepository
    }
}
