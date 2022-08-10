package com.example.germinadorklein.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@ExperimentalMaterial3Api
@Composable
fun DetalleBandejaScreen(seed: String?, navController: NavController) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text("Semilla #$seed") },
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}){
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, null)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                icons = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Delete, null)
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {},
                        elevation = BottomAppBarDefaults.floatingActionButtonElevation()
                    ) {
                        Icon(Icons.Default.Edit, null)
                    }
                }
            )
        }
    ) { paddingValues ->
        DetalleBody(seed = seed, modifier = Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .padding(5.dp))
    }
}

@Composable
fun DetalleBody(seed: String?, modifier: Modifier) {
    Column() {
        Text("Estas viendo la semilla #$seed", style = MaterialTheme.typography.displayMedium)
    }
}