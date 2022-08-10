package com.example.germinadorklein

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.germinadorklein.navigation.AppNavigation
import com.example.germinadorklein.screens.BandejasScreen
import com.example.germinadorklein.ui.theme.GerminadorKleinTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    GerminadorKleinTheme(){
        AppNavigation()
    }
}