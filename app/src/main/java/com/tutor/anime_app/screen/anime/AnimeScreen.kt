package com.tutor.anime_app.screen.anime

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun AnimeScreen(
	id: Int,
	coverImage: String,
	viewModel: AnimeViewModel = hiltViewModel<AnimeViewModel>(),
	modifier: Modifier = Modifier
) {
	LaunchedEffect(key1 = true) {
		viewModel.getAnimeById(id)
	}
	val anime by viewModel.anime.collectAsState()
	Scaffold() { innerPadding ->

		LazyColumn(
			modifier.padding(innerPadding.calculateBottomPadding() + 10.dp)
		) {
			item {
				AsyncImage(
					model = coverImage,
					contentDescription = id.toString(),
					modifier = modifier
						.fillMaxWidth()
						.height(300.dp)
						.clip(
							RoundedCornerShape(20.dp),
						)
				)
			}


			item {
				if (anime == null) {
					Text("Anime is Not found")
				} else {
					Column(
						modifier = modifier
							.fillMaxWidth()
							.padding(20.dp),
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						Text(
							text = "${anime?.attributes?.canonicalTitle}",
							style = MaterialTheme.typography.displaySmall,
							fontWeight = FontWeight.Bold,
							textAlign = TextAlign.Center
						)
						Row() {
							Text(
								text = anime?.attributes?.startDate
									?.split("-")
									?.first()
									.toString(),
								style = MaterialTheme.typography.titleMedium,
								fontWeight = FontWeight.Medium
							)
							Text(
								text = " - ",
								modifier = modifier.padding(horizontal = 5.dp)
							)
							Rating(anime)
							Spacer(modifier.height(16.dp))
						}

						Column(horizontalAlignment = Alignment.Start) {
							Text(
								text = "Synopsis",
								style = MaterialTheme.typography.titleLarge,
								fontWeight = FontWeight.Bold
							)
							Text(
								text = anime?.attributes?.synopsis.toString(),
								style = MaterialTheme.typography.bodyMedium
							)

						}
					}
				}
			}
		}

	}
}

