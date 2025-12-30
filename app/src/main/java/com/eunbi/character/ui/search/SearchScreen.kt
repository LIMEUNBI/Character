package com.eunbi.character.ui.search

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.eunbi.character.R
import com.eunbi.character.navigation.NavConst
import com.eunbi.character.ui.list.ListItem
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel) {

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is SearchContract.Effect.MoveToBack -> {
                    navController.popBackStack()
                }

                is SearchContract.Effect.MoveToDetail -> {
                    val json = Uri.encode(Gson().toJson(it.character))
                    navController.navigate("${NavConst.DETAIL}/$json")
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
                                text = stringResource(R.string.top_bar_title_search),
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
                                        viewModel.setEvent(SearchContract.Event.ClickToBack)
                                    }
                            )
                        }
                    }
                )
            }
        ) { padding ->
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(color = Color.White)
            ) {
                val (textField, list) = createRefs()

                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .background(color = Color.LightGray, shape = RoundedCornerShape(32.dp))
                        .height(50.dp)
                        .constrainAs(textField) {
                            top.linkTo(parent.top)
                        },
                    value = viewModel.viewState.value.searchKeyword,
                    onValueChange = {
                        viewModel.setEvent(
                            SearchContract.Event.ChangeSearchKeyword(
                                it
                            )
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.txt_field_search_label),
                            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search Icon"
                        )
                    },
                    shape = RoundedCornerShape(32.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    textStyle = TextStyle(fontSize = 14.sp)
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(color = Color.White)
                        .constrainAs(list) {
                            top.linkTo(textField.bottom, 8.dp)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        }
                ) {
                    items(state.characterInfo.results) { item ->
                        ListItem(item = item, itemClickListener = {
                            viewModel.setEvent(SearchContract.Event.ClickToDetail(item))
                        })
                    }
                }
            }
        }
    }
}