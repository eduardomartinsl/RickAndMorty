package br.com.compose.rickandmortyapp.di

import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepository
import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepositoryImpl
import br.com.compose.rickandmortyapp.data.service.RickAndMortyService
import br.com.compose.rickandmortyapp.data.service.RickAndMortyServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class GeneralModule {

    companion object {
        private const val BASE_URL = "TODO(falta implementar)"
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Companion.BASE_URL)
        .client(okHttpClient)
        .build()


    @Binds
    abstract fun bindRickAndMortyService(service: RickAndMortyServiceImpl): RickAndMortyService

    @Binds
    abstract fun bindRickAndMortyRepository(repository: RickAndMortyRepositoryImpl): RickAndMortyRepository
}