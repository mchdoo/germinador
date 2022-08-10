package com.example.germinadorklein.screens

import android.view.Menu
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.germinadorklein.R
import com.example.germinadorklein.navigation.Screen

val SPACE = 5.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BandejasScreen(navController: NavController) {

    var selectedItem by remember { mutableStateOf(0) }

    Scaffold (topBar = { MainTopBar("Bandeja # 1") }) { it ->
        ScreenBody(scaffoldPadding = it, navController = navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenBody(scaffoldPadding: PaddingValues, navController: NavController) {

    var dialogOpen by remember { mutableStateOf<Boolean>(false) }

    var selectedCell: String by rememberSaveable {mutableStateOf("1:1")}
    var inactiveCells: MutableList<String> = remember { mutableStateListOf("0") }

    var cells = HashMap<Char, HashMap<Int, Char>>()
    var cell = HashMap<Int, Char>()
    var c: Char = 'A'
    var n: Int = 8;

    for (i in 0..n) {
        cell[i] = c
    }
    for (i in 0..n*2) {
        cells[c] = cell
        c++
    }

    LazyHorizontalGrid(rows = GridCells.Fixed(16), contentPadding = PaddingValues(top = scaffoldPadding.calculateTopPadding(), start = SPACE, end = SPACE, bottom = SPACE), content = {


        items (150) { index ->

            var itemActivated by remember {mutableStateOf<Boolean>(true)}
            val bgColor = if (itemActivated) MaterialTheme.colorScheme.primary else Color.Transparent
            val borderWidth: Dp = if (itemActivated) 0.dp else 1.dp
            val textColor: Color = if (itemActivated) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary

            fun clickCell(e: String) {
                if (itemActivated) {
                    selectedCell = e
                    dialogOpen = true
                }
                if (!itemActivated) {
                    inactiveCells.remove(e)
                }
            }

            if (inactiveCells.contains(index.toString())) {
                itemActivated = false
            }

            Cell(index = index.toString(), bgColor = bgColor, borderWidth = borderWidth, textColor = textColor, onClick = { clickCell(index.toString()) })

        }
    })

    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = { dialogOpen = false },
            title = {
                Text("Cambiar semilla #$selectedCell")
            },
            text = {
                Text("Querés editar o eliminar a la semilla seleccionada?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        navController.navigate(Screen.DetalleBandeja.withArgs(selectedCell.toString()))
                    }
                ) {
                    Text("Editar")
                }
            },
            dismissButton = {
                FilledTonalButton(onClick = {
                    dialogOpen = false
                    inactiveCells.add(selectedCell)
                }) {
                    Text("Eliminar")
                }
            },
            icon = {
                Icon(painterResource(R.drawable.leaf) , null)
            }
        )
    }
}

@Composable
fun Cell(onClick: () -> Unit, index: String, bgColor: Color, borderWidth: Dp, textColor: Color) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(SPACE)
            .background(bgColor, shape = CircleShape)
            .border(
                width = borderWidth,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .padding(SPACE * 3)
            .clickable(
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ){
        Text(text = index, color = textColor)
    }
}

@Composable
fun MainTopBar(title: String) {
    CenterAlignedTopAppBar(
        title = {Text(title)},
        navigationIcon = {IconButton(onClick= { }){
            Icon(Icons.Default.Menu, null)
        }}
    )
}

@Composable
fun DrawerBody() {
    Column(
        modifier = Modifier.padding(SPACE)
    ) {
        Text("Quick Settings", style = MaterialTheme.typography.titleMedium)
    }
}