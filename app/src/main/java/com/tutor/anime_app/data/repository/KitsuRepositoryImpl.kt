package com.tutor.anime_app.data.repository

import android.util.Log
import com.tutor.anime_app.data.network.KitsuApi
import com.tutor.anime_app.domain.model.AnimeData
import com.tutor.anime_app.domain.repository.KitsuRepository
import okio.IOException

class KitsuRepositoryImpl(private val api: KitsuApi) : KitsuRepository {
	override suspend fun getTendingAnimeList(): List<AnimeData> {
		val response = try {
			api.getTrendingAnimeList()
		} catch (e: Exception) {
			Log.d("TAG", "getTendingAnimeList: ${e.message}")
			return emptyList()
		} catch (e: retrofit2.HttpException) {
			Log.d("TAG", "getTendingAnimeList: ${e.message}")
			return emptyList()
		} catch (e: IOException) {
			Log.d("TAG", "getTendingAnimeList: ${e.message}")
			return emptyList()
		}
		return if (response.isSuccessful) {
			response.body()?.data?.map { it.toModel() } ?: emptyList()
		} else {
			emptyList()
		}
	}

	override suspend fun getAnimeById(id: Int): AnimeData? {
		val response = try {
			 api.getAnimeById(id)
		} catch (e: Exception) {
			Log.d("TAG", "getTendingAnimeList: ${e.message}")
			return null
		} catch (e: retrofit2.HttpException) {
			Log.d("TAG", "getTendingAnimeList: ${e.message}")
			return null
		} catch (e: IOException) {
			Log.d("TAG", "getTendingAnimeList: ${e.message}")
			return null
		}
		return if (response.isSuccessful) {
			response.body()?.data?.toModel()
		} else {
			null
		}
	}
}