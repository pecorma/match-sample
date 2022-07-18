package com.mjpecora.app.matchsample.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.mjpecora.app.domain.model.MarsRover

@Composable
fun Home(homeViewModel: HomeViewModel) {
    val state by homeViewModel.uiState.collectAsState()

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ApodImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .fillMaxHeight(.30f),
                state
            )

            LazyMarsPhotosColumn(
                modifier = Modifier.fillMaxSize(),
                state = state
            )
        }
    }
}

@Composable
fun LazyMarsPhotosColumn(
    modifier: Modifier = Modifier,
    state: HomeUiState
) {
    val lazyPhotos = (state as? HomeUiState.Loaded)?.marsRoverFlow?.collectAsLazyPagingItems()
    LazyColumn(modifier = modifier) {
        if (lazyPhotos != null) {
            items(lazyPhotos) { item ->
                item?.let {
                    MarsRoverCard(marsRover = it)
                    Spacer(modifier = Modifier.height(6.dp))
                    Divider(color = Color.White)
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}

@Composable
fun MarsRoverCard(
    modifier: Modifier = Modifier,
    marsRover: MarsRover
) {
    Box(
        modifier = modifier.padding(4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = marsRover.imgSrc,
                contentDescription = "",
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.wrapContentSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "By rover ${marsRover.roverName}",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Earth date: ${marsRover.date}  |  Sol Date: ${marsRover.sol}",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

}


@Composable
fun ApodImage(
    modifier: Modifier = Modifier,
    state: HomeUiState
) {
    Box(
        modifier = modifier
            .background(
                Brush.verticalGradient(
                listOf(
                    Color.Transparent,
                    Color.Black.copy(alpha = 0.83f)
                )
            )
        ),
        contentAlignment = Alignment.BottomCenter,
    ) {
        AsyncImage(
            model = (state as? HomeUiState.Loaded)?.apodData?.url,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentScale = ContentScale.FillBounds
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text ="By ${(state as? HomeUiState.Loaded)?.apodData?.copyright ?: "Jake P."}",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "On ${(state as? HomeUiState.Loaded)?.apodData?.date ?: "UNKNOWN"}",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.width(12.dp))
            Divider(
                modifier = Modifier.width(1.dp).height(30.dp),
                color = Color.White
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.wrapContentWidth(),
                text = (state as? HomeUiState.Loaded)?.apodData?.title ?: "No title",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }

}