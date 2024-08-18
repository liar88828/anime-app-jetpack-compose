package com.tutor.anime_app.screen.anime_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tutor.anime_app.domain.model.AnimeData

@Composable
fun AnimeListItem(
	item: AnimeData,
	onClick: (String, String) -> Unit,
	modifier: Modifier = Modifier
) {

	Card(onClick = {
		onClick(
			item.attributes.posterImage.original,
			item.id
		)
	}, modifier = modifier) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.spacedBy(10.dp),
			modifier = modifier.padding(6.dp)
		) {
			AsyncImage(
				model = item.attributes.posterImage.original,
				contentDescription = item.id,
				modifier = modifier
					.size(100.dp)
					.clip(RoundedCornerShape(10.dp)), contentScale = ContentScale.Crop
			)
			Column() {
				RatingHome(item)
				Text(
					text = item.attributes.canonicalTitle,
					style = MaterialTheme.typography.titleMedium,
				)
				Text(
					text = item.attributes.synopsis,
					maxLines = 3,
					overflow = TextOverflow.Ellipsis,
					style = MaterialTheme.typography.bodySmall
				)
//				Text(text = item.attributes.titles.en ?: "", maxLines = 1)
//				Text(text = item.attributes.episodeCount)
//				Text(text = item.attributes.startDate)
//				Text(text = item.attributes.endDate)
//				Text(text = item.attributes.ageRating)
//				Text(text = item.attributes.ageRatingGuide)
//				Text(text = item.attributes.status)
//				Text(text = item.attributes.)
			}
		}
	}
}

@Composable
private fun RatingHome(
	item: AnimeData,
	modifier: Modifier = Modifier,
) {
	Row(
		modifier = modifier
			.background(
				Color.Yellow.copy(alpha = 0.5f),
				RoundedCornerShape(30.dp)
			)
			.padding(5.dp),
	) {
		Icon(
			imageVector = Icons.Default.Star,
			contentDescription = "Icon start",
			tint = Color.Yellow
		)
		Text(text = item.attributes.averageRating)
	}
}