package com.mjpecora.app.matchsample.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun Home(homeViewModel: HomeViewModel) {
    val state = homeViewModel.state.collectAsState(initial = HomeState())
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .background(Color.Black),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            ApodImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(.92f)
                    .fillMaxHeight(.25f),
                state.value.apod?.url ?: ""
            )
        }
    }
}

@Composable
fun ApodImage(
    modifier: Modifier = Modifier,
    m: String
) {
    AsyncImage(
        model = m,
        contentDescription = "",
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White),
        contentScale = ContentScale.Fit
    )
}