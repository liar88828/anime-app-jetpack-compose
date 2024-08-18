package com.tutor.anime_app.screen.anime_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.anime_app.domain.model.AnimeData
import com.tutor.anime_app.domain.repository.KitsuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingAnimeListViewModel
@Inject constructor(
	private val repository: KitsuRepository
) : ViewModel() {
	private val _animeList = MutableStateFlow<List<AnimeData>>(emptyList())
	val animeList = _animeList.asStateFlow()

	init {
		viewModelScope.launch {
			Log.d("Launch", "init: test")
			_animeList.update {
				repository.getTendingAnimeList()
			}
		}
	}

}