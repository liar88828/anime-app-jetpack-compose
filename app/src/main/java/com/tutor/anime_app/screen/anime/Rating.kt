package com.tutor.anime_app.screen.anime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tutor.anime_app.domain.model.AnimeData

@Composable
fun Rating(anime: AnimeData?) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(1.dp),
	) {

		Icon(
			imageVector = Icons.Default.Star,
			contentDescription = "Icon start",
			tint = Color.Yellow
		)
		Text(
			text = anime?.attributes?.averageRating.toString(),
			style = MaterialTheme.typography.titleMedium,
			fontWeight = FontWeight.Medium
		)

	}
}