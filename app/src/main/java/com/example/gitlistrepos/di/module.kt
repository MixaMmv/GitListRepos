package com.example.gitlistrepos.di

import com.example.gitlistrepos.data.api.ReposApi
import com.example.gitlistrepos.data.api.ReposRemoteSource
import com.example.gitlistrepos.data.api.ReposRepository
import com.example.gitlistrepos.data.api.ReposRepositoryImpl
import com.example.gitlistrepos.domain.ReposInteractor
import com.example.gitlistrepos.features.commits_info.ui.CommitsInfoViewModel
import com.example.gitlistrepos.features.repos_list.ui.ReposListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


const val BASE_URL = "https://api.github.com/"


val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) } )
            .build()
    }


    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<ReposApi> {
        get<Retrofit>().create()
    }

    single<ReposRemoteSource> {
        ReposRemoteSource(get<ReposApi>())
    }

    single<ReposRepository> {
        ReposRepositoryImpl(get<ReposRemoteSource>())
    }

    single<ReposInteractor> {
        ReposInteractor(get<ReposRepository>())
    }

    viewModel<ReposListViewModel> {
        ReposListViewModel(get<ReposInteractor>())
    }

    viewModel<CommitsInfoViewModel> {
        CommitsInfoViewModel(get<ReposInteractor>())
    }


}