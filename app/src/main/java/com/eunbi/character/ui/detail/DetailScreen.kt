package com.eunbi.character.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.eunbi.character.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, viewModel: DetailViewModel) {

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is DetailContract.Effect.MoveToBack -> {
                    navController.popBackStack()
                }
            }
        }
    }

    viewModel.viewState.value.let { state ->
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            topBar = {
                TopAppBar(
                    modifier = Modifier.height(56.dp),
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                    windowInsets = WindowInsets(left = 0.dp, right = 0.dp),
                    title = {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.top_bar_title_detail),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    },
                    navigationIcon = {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_back),
                                contentDescription = "back",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        viewModel.setEvent(DetailContract.Event.ClickToBack)
                                    }
                            )
                        }
                    }
                )
            }
        ) { padding ->
            ConstraintLayout(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(color = Color.White)
            ) {

                val (image, name, status, gender, species, originText, originName, originUrl, locationText, locationName, locationUrl, created) = createRefs()

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.character.image)
                        .placeholder(R.drawable.ic_empty)
                        .error(R.drawable.ic_empty)
                        .crossfade(true)
                        .build(),
                    contentDescription = state.character.name,
                    modifier = Modifier
                        .size(320.dp)
                        .padding(8.dp)
                        .constrainAs(image) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                        },
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = stringResource(R.string.item_detail_name, state.character.name),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(name) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(image.bottom)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = stringResource(R.string.item_detail_status, state.character.status),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(status) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(name.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = stringResource(R.string.item_detail_gender, state.character.gender),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(gender) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(status.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = stringResource(R.string.item_detail_species, state.character.species),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(species) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(gender.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = stringResource(R.string.item_detail_origin),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(originText) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(species.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = state.character.origin.name,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .constrainAs(originName) {
                            start.linkTo(originText.start, 8.dp)
                            top.linkTo(originText.bottom, 2.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = state.character.origin.url,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .constrainAs(originUrl) {
                            start.linkTo(originText.start, 8.dp)
                            top.linkTo(originName.bottom, 2.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = stringResource(R.string.item_detail_location),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(locationText) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(originUrl.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = state.character.location.name,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .constrainAs(locationName) {
                            start.linkTo(locationText.start, 8.dp)
                            top.linkTo(locationText.bottom, 2.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = state.character.location.url,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .constrainAs(locationUrl) {
                            start.linkTo(locationText.start, 8.dp)
                            top.linkTo(locationName.bottom, 2.dp)
                            width = Dimension.fillToConstraints
                        }
                )

                Text(
                    text = stringResource(R.string.item_detail_created, state.character.created),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .constrainAs(created) {
                            start.linkTo(parent.start, 12.dp)
                            top.linkTo(locationUrl.bottom, 4.dp)
                            width = Dimension.fillToConstraints
                        }
                )
            }
        }
    }
}