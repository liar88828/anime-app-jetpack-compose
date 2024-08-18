package com.tutor.anime_app.domain.repository

import com.tutor.anime_app.domain.model.AnimeData

interface KitsuRepository {
	 suspend fun getTendingAnimeList(): List<AnimeData>
	suspend fun getAnimeById(id: Int): AnimeData?
}