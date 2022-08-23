package com.example.noteapp.di

import android.content.Context
import androidx.room.Room
import com.example.noteapp.data.network.RickAndMortyApiService
import com.example.noteapp.data.repository.NoteRepositoryImpl
import com.example.noteapp.data.room.NoteDao
import com.example.noteapp.data.room.NoteDataBase
import com.example.noteapp.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            NoteDataBase::class.java,
            "note_database"
        ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDataBase: NoteDataBase) = noteDataBase.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao,rickAndMortyApiService: RickAndMortyApiService): NoteRepository {
        return NoteRepositoryImpl(noteDao, rickAndMortyApiService)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("BASE_URL")
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient().newBuilder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}