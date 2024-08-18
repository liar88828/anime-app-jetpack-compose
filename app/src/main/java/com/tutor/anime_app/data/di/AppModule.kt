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

//	@Provides
//	@Singleton
//	fun providerOkHttpClient(): OkHttpClient {
//		val okHttpClient = OkHttpClient.Builder()
//		okHttpClient.readTimeout(30, TimeUnit.SECONDS)
//		okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
//		okHttpClient.writeTimeout(90, TimeUnit.SECONDS)
//		return okHttpClient.build()
//
//	}

	@Provides
	@Singleton
	fun providerRetrofit(
//		okHttpClient: OkHttpClient
	): Retrofit {
		return Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
//			.client(okHttpClient)
			.baseUrl(KitsuApi.baseUrl)
			.build()

	}

	@Provides
	@Singleton
	fun providerKitsuApi(retrofit: Retrofit): KitsuApi {
		return retrofit
			.create(KitsuApi::class.java)
	}

	@Provides
	@Singleton
	fun providerKitsuRepository(api: KitsuApi): KitsuRepository {
		return KitsuRepositoryImpl(api)
	}
}