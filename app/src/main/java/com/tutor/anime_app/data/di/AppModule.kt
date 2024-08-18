package com.tutor.anime_app.data.di

import com.tutor.anime_app.data.network.KitsuApi
import com.tutor.anime_app.data.repository.KitsuRepositoryImpl
import com.tutor.anime_app.domain.repository.KitsuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



	@Provides
	@Singleton
	fun providerRetrofit(): Retrofit {
		return Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.baseUrl(KitsuApi.baseUrl)
			.build()

	}

	@Provides
	@Singleton
	fun providerKitsuApi(retrofit: Retrofit):KitsuApi{
		return retrofit
			.create(KitsuApi::class.java)
	}

	@Provides
	@Singleton
	fun providerKitsuRepository(api: KitsuApi): KitsuRepository {
		return KitsuRepositoryImpl(api)
	}
}