package com.alex.lingvatranslate.ui.languageSelection


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageSelectionView(
    viewModel: LanguageSelectionViewModel = viewModel(),
    navigateUp: () -> Unit
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        LargeTopAppBar(
            title = { Text(text = "Translate from") },
            navigationIcon = {
                IconButton(onClick = { navigateUp() }) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                }
            }
        )

        LazyColumn(Modifier.padding(start = 5.dp)) {
            items(viewState.languages) {

                Box(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}