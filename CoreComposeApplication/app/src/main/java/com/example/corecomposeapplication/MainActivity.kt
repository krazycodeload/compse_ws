package com.example.corecomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.corecomposeapplication.ui.theme.CoreComposeApplicationTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          MainScreen()
        }
    }
}

@Composable
fun MainScreen(){
    val greetingListState = remember {
        mutableStateListOf("John", "Amanda")
    }
//    Surface(color = Color.DarkGray, modifier = Modifier.fillMaxSize()) {
//        Surface(color = Color.Magenta, modifier = Modifier.wrapContentSize(align = Alignment.Center)) {
//            Text(text = "Wrapped content",
//               // modifier = Modifier.wrapContentSize(),
//                style = MaterialTheme.typography.headlineMedium)
//        }
//    }
//    Row(modifier = Modifier.fillMaxSize(),
//        horizontalArrangement = Arrangement.Center,
//        verticalAlignment = Alignment.CenterVertically
//        //verticalAlignment = Alignment.Bottom
//    ) {
//        HorizontalColorBar(Color.Magenta)
//        HorizontalColorBar(Color.Cyan)
//        HorizontalColorBar(Color.Red)
//        HorizontalColorBar(Color.Green)
//        HorizontalColorBar(Color.Blue)
////        HorizontalColorBar(Color.Yellow)
////        HorizontalColorBar(Color.Gray)
////        Surface(color = Color.Magenta, modifier = Modifier
////            .height(600.dp)
////            .width(60.dp)
////        ) {
////
////        }
////        Surface(color = Color.Red, modifier = Modifier
////            .height(600.dp)
////            .width(60.dp)) {
////
////        }
//    }

//    Column(modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//        //verticalAlignment = Alignment.Bottom
//    ) {
//        VerticalColorBar(Color.Magenta)
//        VerticalColorBar(Color.Cyan)
//        VerticalColorBar(Color.Red)
//        VerticalColorBar(Color.Green)
//        VerticalColorBar(Color.Blue)
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(greetingListState) {
            greetingListState.add("Michael")
        }
    }


}

@Composable
fun GreetingList(namesList: List<String>, buttonClick: () -> Unit) {
    for(name in namesList) {
        Greeting(name = name)
    }

    Button(onClick = buttonClick) {
        Text("Add new name")
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.headlineMedium
    )
}



@Composable
fun HorizontalColorBar(color: Color){
    Surface(color = color, modifier = Modifier
        .height(620.dp)
        .width(60.dp)) {

    }
}

@Composable
fun VerticalColorBar(color: Color){
    Surface(color = color, modifier = Modifier
        .height(100.dp)
        .width(320.dp)) {

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        MainScreen()
}