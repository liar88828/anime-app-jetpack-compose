package com.tutor.anime_app.screen.anime_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tutor.anime_app.screen.anime_list.component.AnimeListItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TrendingAnimeListScreen(
	onClick: (cover: String, id: String) -> Unit,
	viewModel: TrendingAnimeListViewModel = hiltViewModel<TrendingAnimeListViewModel>(),
	animatedVisibilityScope: AnimatedVisibilityScope,
	modifier: Modifier = Modifier,
) {
	val animeList by viewModel.animeList.collectAsState()
	Scaffold { innerPadding ->
		Box(
			modifier = modifier
				.fillMaxSize()
				.padding(innerPadding)
		) {
			AnimatedContent(
				targetState = animeList.isEmpty(),
				label = ""
			) { isEmpty ->
				if (isEmpty) {
					Box(
						modifier = modifier.fillMaxSize(),
						contentAlignment = Alignment.Center
					) {
						CircularProgressIndicator()
					}
				} else {
					LazyColumn(
						verticalArrangement = Arrangement.spacedBy(8.dp),
						contentPadding = PaddingValues(
							20.dp
						),
						modifier = modifier.fillMaxSize()
					) {
						item() {
							Text(
								text = "Trending Anime",
								style = MaterialTheme.typography.displaySmall,
								fontWeight = FontWeight.Bold
							)
						}
						items(animeList) {
							AnimeListItem(
								item = it,
								onClick = {
									onClick(
										it.attributes.posterImage.original,
										it.id
									)
								},
								animatedVisibilityScope = animatedVisibilityScope
							)
						}
					}
				}

			}
		}

	}
}

