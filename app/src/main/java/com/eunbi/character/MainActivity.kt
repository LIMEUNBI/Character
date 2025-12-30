package com.eunbi.character

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.eunbi.character.navigation.NavConst
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eunbi.character.model.Character
import com.eunbi.character.ui.detail.DetailContract
import com.eunbi.character.ui.detail.DetailScreen
import com.eunbi.character.ui.detail.DetailViewModel
import com.eunbi.character.ui.list.ListScreen
import com.eunbi.character.ui.list.ListViewModel
import com.eunbi.character.ui.search.SearchScreen
import com.eunbi.character.ui.search.SearchViewModel
import com.eunbi.character.ui.theme.CharacterTheme
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterTheme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavConst.LIST,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavConst.LIST) {
                            val viewModel = hiltViewModel<ListViewModel>()
                            ListScreen(navController, viewModel)
                        }

                        composable("${NavConst.DETAIL}/{${NavConst.CHARACTER_INFO}}",
                            arguments = listOf(navArgument(NavConst.CHARACTER_INFO) {
                                type = NavType.StringType
                            })
                        ) {
                            val info = it.arguments?.getString(NavConst.CHARACTER_INFO)
                            val character = Gson().fromJson(info, Character::class.java)
                            val viewModel = hiltViewModel<DetailViewModel>()

                            character?.let { character ->
                                viewModel.setEvent(DetailContract.Event.SetInfo(character))
                            }
                            DetailScreen(navController, viewModel)
                        }

                        composable(NavConst.SEARCH) {
                            val viewModel = hiltViewModel<SearchViewModel>()
                            SearchScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}