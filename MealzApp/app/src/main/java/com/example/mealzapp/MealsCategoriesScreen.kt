package com.example.mealzapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mealzapp.model.response.MealResponse
import com.example.mealzapp.ui.theme.MealzAppTheme


@Composable
fun MealsCategoriesScreen() {
    val viewModel: MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealsState.value
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(meals) { meal ->
            MealCategory(meal)
        }
    }
}
@OptIn(ExperimentalCoilApi::class)
@Composable
fun MealCategory(meal: MealResponse) {
    var (expanded, setExpanded) = remember { mutableStateOf(false) }
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
           ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Row {
                Image(
                    painter = rememberImagePainter(meal.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(88.dp)
                        .padding(4.dp)
                        .align(Alignment.CenterVertically)
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                ) {
                    Text(
                        text = meal.name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Text(
                        text = meal.description,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 10
                    )
                }

//                Column(
//                    modifier = Modifier
//                        .align(Alignment.CenterVertically)
//                        .padding(10.dp)
//                ) {
//                    Text(
//                        text = meal.description,
//                        style = MaterialTheme.typography.bodySmall,
//                        maxLines = 2
//                    )
//                }

                Icon(
                    imageVector = if (expanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expand row icon",
                    modifier = Modifier
                        .padding(16.dp)
                        .align(
                            if (expanded)
                                Alignment.Bottom
                            else
                                Alignment.CenterVertically
                        )
                        .clickable { expanded = !setExpanded(true) }
                )
            }
        }
}

private operator fun Any.getValue(nothing: Nothing?, property: KProperty<*>): Any {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealzAppTheme {
        MealsCategoriesScreen()
    }
}
