package com.tutor.anime_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.tutor.anime_app.screen.anime.AnimeScreen
import com.tutor.anime_app.screen.anime_list.TrendingAnimeListScreen
import com.tutor.anime_app.ui.theme.AnimeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge(
			statusBarStyle = SystemBarStyle.dark(
				android.graphics.Color.TRANSPARENT
			)
		)
		setContent {
			AnimeAppTheme {
				val navController = rememberNavController()
				NavHost(
					navController = navController,
					startDestination = AnimeHome
				) {
					composable<AnimeHome> {
						TrendingAnimeListScreen(
							onClick = { coverUrl, id ->
								navController.navigate(
									AnimeDetails(id, coverUrl)
								)
							}
						)
					}
					composable<AnimeDetails>() { backStackEntry ->
//						val args = it.toRoute<AnimeDetail>()
						val args = backStackEntry.toRoute<AnimeDetails>()
						AnimeScreen(
							id = args.id.toInt(),
							coverImage = args.coverImage
//							id = 12,
//							coverImage = "https://media.kitsu.app/anime/poster_images/12/tiny.jpg"
						)
					}
				}

			}
		}
	}
}

@Serializable
data object AnimeHome

@Serializable// fact : cant be initial
data class AnimeDetails(
	val id: String, val coverImage: String
) {

}