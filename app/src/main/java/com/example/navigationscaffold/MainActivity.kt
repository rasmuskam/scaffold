package com.example.navigationScaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.navigationscaffold.ui.theme.NavigationScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationScaffoldTheme {
                val navController = rememberNavController()
                ScaffoldApp(navController)
            }
        }
    }
}

@Composable
fun ScaffoldApp(navController: NavHostController) {
    Scaffold(
        topBar = {
            MainTopBar(
                title = "My App",
                onMenuClick = { /* Open the menu if needed */ },
                navController = navController
            )
        },
        content = { paddingValues ->
            NavHost(navController, startDestination = "main", Modifier.padding(paddingValues)) {
                composable("main") { MainScreenContent(navController) }
                composable("info") { InfoScreen(navController) }
                composable("settings") { SettingsScreen(navController) }
            }
        }
    )
}

@Composable
fun MainScreenContent(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Main Screen",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = { navController.navigate("info") }) {
            Text("Go to Info")
        }

        Button(onClick = { navController.navigate("settings") }) {
            Text("Go to Settings")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, onMenuClick: () -> Unit, navController: NavHostController) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = onMenuClick) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavHostController) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun InfoScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        ScreenTopBar(title = "Info", navController = navController)

        Text(
            text = "Info Screen Content",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        ScreenTopBar(title = "Settings", navController = navController)

        Text(
            text = "Settings Screen Content",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationScaffoldTheme {
        ScaffoldApp(rememberNavController())
    }
}