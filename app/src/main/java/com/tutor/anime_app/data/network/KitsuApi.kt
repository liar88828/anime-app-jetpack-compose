package com.tutor.anime_app.data.network

import com.tutor.anime_app.data.network.dto.AnimeResponseDto
import com.tutor.anime_app.data.network.dto.TrendingAnimeListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface KitsuApi {
	@GET("trending/anime/")
	suspend fun getTrendingAnimeList(): TrendingAnimeListDto

	@GET("anime/{id}")
	suspend fun getAnimeById(@Path("id") id: Int): AnimeResponseDto

	companion object {
		const val baseUrl = "https://kitsu.io/api/edge/"
	}

}