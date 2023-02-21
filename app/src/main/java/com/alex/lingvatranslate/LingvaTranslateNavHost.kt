package com.alex.lingvatranslate

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alex.lingvatranslate.ui.languageSelection.LanguageSelectionView
import com.alex.lingvatranslate.ui.languageSelection.LanguageSelectionViewModel
import com.alex.lingvatranslate.ui.translate.TranslateView
import com.alex.lingvatranslate.ui.translate.TranslateViewModel

@Composable
fun LingvaTranslateNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Translate.route,
        modifier = modifier
    ) {
        composable(route = Translate.route) {
            val viewModel = hiltViewModel<TranslateViewModel>()

            TranslateView(
                viewModel = viewModel,
                openLanguageSelectionView = {
                    navController.navigate(Languages.route)
                }
            )
        }
        composable(route = Languages.route) {
            val viewModel = hiltViewModel<LanguageSelectionViewModel>()

            LanguageSelectionView(
                viewModel = viewModel,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}