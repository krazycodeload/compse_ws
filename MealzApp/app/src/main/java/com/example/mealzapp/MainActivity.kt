package com.example.mealzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealzAppTheme {
                MealsCategoriesScreen()
            }
        }
    }
}

//@Composable
//fun MealsCategoriesScreen() {
//    val viewModel: MealsCategoriesViewModel = viewModel()
//   // var meals = viewModel.getMeals()
//    val meals = viewModel.mealsState.value
//    Text(text = "Hello Compose!")
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MealzAppTheme {
//        MealsCategoriesScreen()
//    }
//}