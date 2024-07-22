package com.example.basicnavigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           NavigationBasicApp()
        }
    }

    @Composable
    fun NavigationBasicApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { HomeScreen(navController) }
            composable("dashboard/{userName}") { backStackEntry ->
                DashBoardScreen(navController, backStackEntry.arguments?.getString("userName"))
            }
        }
    }

    @Composable
    fun HomeScreen(navController: NavHostController) {
        val context = LocalContext.current
        var name by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Your first name") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = {
                    Log.d("isValidName",isValidName(name).toString())
                    if (isValidName(name)) {
                        navController.navigate("dashboard/${name}")
                    }
                }
            ) {
                Text(text = "Enter")
            }
        }
    }

    fun isValidName(name: String): Boolean {
        //return !name.trim().isNullOrEmpty() && name.all { it.isLetter() }

        return !name.trim().isNullOrEmpty()
    }

    @Composable
    fun DashBoardScreen(navController: NavHostController, userName: String?) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text("Welcome ${userName}!")

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Button(onClick = { navController.navigate("home") }) {
                Text(text = "Exit")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        NavigationBasicApp()
    }



}



