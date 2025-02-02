package com.example.binapp.di

import com.example.binapp.data.api.BinListAPIService
import com.example.binapp.data.local.database.BinDatabase
import com.example.binapp.data.repository.BinRepository
import com.example.binapp.domain.repository.BinRepositoryInterface
import com.example.binapp.domain.usecase.DeleteBinHistoryUseCase
import com.example.binapp.domain.usecase.GetBinHistoryUseCase
import com.example.binapp.domain.usecase.GetBinInfoUseCase
import com.example.binapp.presentation.viewmodel.BinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single<BinRepositoryInterface> {BinRepository(get(), get())}
    factory { GetBinInfoUseCase(get<BinRepositoryInterface>()) }
    factory { GetBinHistoryUseCase(get<BinRepositoryInterface>()) }

    single<BinListAPIService> {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinListAPIService::class.java)
    }
    single { BinDatabase.getDatabase(get()) }
    single { get<BinDatabase>().binDao() }

    single { BinRepository(get(), get()) }

    single { GetBinInfoUseCase(repository = get()) }
    single { GetBinHistoryUseCase(repository = get()) }
    single { DeleteBinHistoryUseCase(repository = get()) }
    viewModel { BinViewModel(getBinInfoUseCase = get(),
        getHistoryUseCase = get(),
        deleteHistoryUseCase = get()) }
}


val domainModule = module {
    factory { GetBinInfoUseCase(get<BinRepositoryInterface>()) }
    factory { GetBinHistoryUseCase(get<BinRepositoryInterface>()) }
    single<BinListAPIService> {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinListAPIService::class.java)
    }
    single { BinDatabase.getDatabase(get()) }
    single { get<BinDatabase>().binDao() }


}

val binListAPIModule = module {
    single<BinListAPIService> {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinListAPIService::class.java)
    }
}

val databaseModule = module {
    single { BinDatabase.getDatabase(get()) }
    single { get<BinDatabase>().binDao() }
}

val localBinRepository = module {
    single { BinRepository(get(), get()) }
}

val presentationModule = module {
    // UseCases



}