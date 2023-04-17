package br.com.compose.rickandmortyapp.di

import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepository
import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepositoryImpl
import br.com.compose.rickandmortyapp.data.service.RickAndMortyService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
abstract class GeneralModule {

    @Binds
    abstract fun bindRickAndMortyRepository(repository: RickAndMortyRepositoryImpl): RickAndMortyRepository

}

@Module
@InstallIn(SingletonComponent::class)
object RickAndMortyServiceModule {

    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideRickAndMortyService(retrofit: Retrofit): RickAndMortyService =
        retrofit.create(RickAndMortyService::class.java)
}