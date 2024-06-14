package com.example.catat.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catat.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.catat.feature_note.presentation.notes.NotesScreen
import com.example.catat.feature_note.presentation.util.Screen
import com.example.catat.theme.CleanArchitectureNoteAppTheme
import com.example.catat.feature_note.data.PreferencesManager
import com.example.catat.maps.ViewMapsScreen // Import ViewMapsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var preferencesManager: PreferencesManager

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferencesManager = PreferencesManager(this)

        setContent {
            CleanArchitectureNoteAppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "splash_screen"
                    ) {
                        composable(route = "splash_screen") {
                            SplashScreen(onTimeout = {
                                navController.navigate(Screen.NotesScreen.route) {
                                    popUpTo("splash_screen") { inclusive = true }
                                }
                            }, context = this@MainActivity)
                        }
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController, preferencesManager = preferencesManager)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = color
                            )
                        }
                        composable(route = Screen.MapScreen.route) {
                            ViewMapsScreen()
                        }
                    }
                }
            }
        }
    }
}
