package br.com.compose.rickandmortyapp.di

import br.com.compose.rickandmortyapp.data.moshi.RMCharacterAdapter
import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepository
import br.com.compose.rickandmortyapp.data.repository.RickAndMortyRepositoryImpl
import br.com.compose.rickandmortyapp.data.service.RickAndMortyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providesInterceptor() = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(RMCharacterAdapter())
        .build()

    @Provides
    @Singleton
    fun provideRickAndMortyService(retrofit: Retrofit): RickAndMortyService =
        retrofit.create(RickAndMortyService::class.java)

    @Provides
    @Singleton
    fun providesRickAndMortyRepository(repository: RickAndMortyRepositoryImpl): RickAndMortyRepository =
        repository

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}