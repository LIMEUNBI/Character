package com.eunbi.character.ui.list

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.eunbi.character.R
import com.eunbi.character.navigation.NavConst
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, viewModel: ListViewModel) {

    val listState = rememberLazyListState()

    LaunchedEffect(listState.canScrollForward) {
        if (!listState.canScrollForward) {
            viewModel.getCharacterList()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is ListContract.Effect.MoveToDetail -> {
                    val json = Uri.encode(Gson().toJson(it.character))
                    navController.navigate("${NavConst.DETAIL}/$json")
                }

                is ListContract.Effect.MoveToSearch -> {
                    navController.navigate(NavConst.SEARCH)
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
                                text = stringResource(R.string.top_bar_title_list),
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
                    actions = {
                        Box(
                            modifier = Modifier.fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_search),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        viewModel.setEvent(ListContract.Event.ClickToSearch)
                                    }
                            )
                        }
                    }
                )
            },
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(color = Color.White),
                state = listState
            ) {
                items(state.characterList.results) { item ->
                    ListItem(item = item, itemClickListener = {
                        viewModel.setEvent(ListContract.Event.ClickToDetail(item))
                    })
                }
            }
        }
    }
}