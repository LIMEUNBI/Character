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
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.eunbi.character.navigation.NavConst
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.eunbi.character.model.CharacterInfo
import com.eunbi.character.ui.theme.CharacterTheme
import com.google.gson.Gson

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

                        }

                        composable("${NavConst.DETAIL}/{${NavConst.CHARACTER_INFO}}",
                            arguments = listOf(navArgument(NavConst.CHARACTER_INFO) {
                                type = NavType.StringType
                            })
                        ) {
                            val info = it.arguments?.getString(NavConst.CHARACTER_INFO)
                            val characterInfo = Gson().fromJson(info, CharacterInfo::class.java)
                        }

                        composable(NavConst.SEARCH) {

                        }
                    }
                }
            }
        }
    }
}