package com.tutor.anime_app.screen.anime

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
class AnimeViewModel
@Inject
constructor(
	private val repository: KitsuRepository
) : ViewModel() {

	private val _anime = MutableStateFlow<AnimeData?>(null)
	val anime = _anime.asStateFlow()

	fun getAnimeById(id: Int) {
		viewModelScope.launch {
			_anime.update { repository.getAnimeById(id) }
		}

	}

}