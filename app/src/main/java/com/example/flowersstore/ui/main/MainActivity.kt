package com.example.flowersstore.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.flowersstore.ui.theme.FlowersStoreTheme

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var title by remember { mutableStateOf("") }

            FlowersStoreTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = title)
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                    },
                    bottomBar = {
                        BottomAppBar {
                            IconButton(onClick = { }) {
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = "Избранное"
                                )
                            }
                            Spacer(Modifier.weight(1f, true))
                            IconButton(onClick = { }) {
                                Icon(
                                    Icons.Filled.Info,
                                    contentDescription = "Информация о приложении"
                                )
                            }
                        }
                    },
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()

                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable("home") {
                                title = "Main"
                                MainScreen(viewModel = mainViewModel, navController)
                            }
                            composable(
                                "product/{id}",
                                arguments = listOf(
                                    navArgument("id") {
                                        type = NavType.IntType
                                    }
                                )
                            ) { args ->
                                title = "Product Details"

                                args.arguments?.getInt("id")?.let { id ->
                                    ProductDetailsScreen(
                                        id,
                                        navController
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
