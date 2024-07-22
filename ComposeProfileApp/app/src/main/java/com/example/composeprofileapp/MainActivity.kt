package com.example.composeprofileapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.composeprofileapp.ui.theme.MyTheme
import com.example.composeprofileapp.ui.theme.lightGreen200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTheme {
                UsersApplication()
              //  UserProfileDetailsScreen()
            }

        }
    }
}

//@Composable
//fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "users_list") {
//        composable("users_list") {
//            UserListScreen(userProfiles, navController)
//        }
//        composable("user_details") {
//            UserProfileDetailsScreen()
//        }
//    }
//}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            UserListScreen(userProfiles, navController)
        }
        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"), navController)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(userProfiles: List<UserProfile> = userProfileList,navController: NavHostController?){
    Scaffold(topBar = {  AppBar(
        title = "Users list",
        icon = Icons.Default.Home
    ) { }}) {
        Surface(modifier = Modifier.fillMaxSize(),
            color = Color.LightGray,
        ) {
            Column {
                Spacer(modifier = Modifier.padding(top = 100.dp))
                LazyColumn {
                 items(userProfiles){ userProfile->
                   //  ProfileCard(userProfile = userProfile)
                     ProfileCard(userProfile = userProfile) {
                         //navController?.navigate("user_details")
                         navController?.navigate("user_details/${userProfile.id}")
                     }
                 }
                }
//                for (userProfile in userProfiles)
//                    ProfileCard(userProfile = userProfile)
            }
           // ProfileCard()
        }
    }

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AppBar() {
//    TopAppBar(
//        navigationIcon = {
//            Icon(
//                Icons.Default.Home,
//                contentDescription = "",
//                modifier = Modifier.padding(horizontal = 12.dp),
//            )
//        },
//        title = { Text("Messaging Application users") }
//
//    )
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                icon,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = { iconClickAction.invoke() })
            )
        },
        title = { Text(title) }
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile,clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable(onClick = { clickAction.invoke() })
            .wrapContentHeight(align = Alignment.Top),
          elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.pictureUrl, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }

    }
}

//@Composable
//fun ProfileCard(userProfile: UserProfile) {
//   // Column {
//       // Spacer(modifier = Modifier.padding(top = 100.dp))
//        Card(
//            modifier = Modifier
//                .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
//                .fillMaxWidth()
//                .wrapContentHeight(align = Alignment.Top),
//            elevation = CardDefaults.cardElevation(
//                defaultElevation = 8.dp
//            ),
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Start
//            ) {
//                ProfilePicture(userProfile.pictureUrl, userProfile.status)
//                ProfileContent(userProfile.name, userProfile.status)
////                ProfilePicture()
////                ProfileContent()
//            }
//
//        }
//   // }
//
//}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if (onlineStatus)
                lightGreen200
            else Color.Red
        ),
        modifier = Modifier
            .padding(16.dp)
            .size(imageSize),
        elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
    ) {
        Image(
            painter = rememberImagePainter(
                data = pictureUrl,
                builder = {
                    transformations(CircleCropTransformation())
                },
            ),
            modifier = Modifier.size(imageSize),
            contentDescription = "Profile picture description",
        )
    }
}

//@Composable
//fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean) {
//    Card(
//        shape = CircleShape,
//        border = BorderStroke(
//            width = 2.dp,
//            color = if (onlineStatus)
//                lightGreen200
//            else Color.Red
//        ),
//        modifier = Modifier.padding(16.dp),
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 4.dp
//        )
//    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(pictureUrl)
//                .crossfade(true)
//                .build(),
//           // placeholder = painterResource(drawableId),
//            contentDescription = "Content description",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.size(72.dp)
//        )
////        Image(
////            painter = painterResource(id = drawableId),
////            modifier = Modifier.size(72.dp),
////            contentScale = ContentScale.Crop,
////            contentDescription = "Content description"
////        )
//    }
//}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean,alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
            //.fillMaxWidth()
            horizontalAlignment = alignment
    ) {
        if (onlineStatus){
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }else{
            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }

        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            Text(
                text = if (onlineStatus)
                    "Active now"
                else "Offline",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileDetailsScreen(userId: Int, navController: NavHostController?) {
    val userProfile = userProfileList.first { userProfile -> userId == userProfile.id }
//fun UserProfileDetailsScreen(userProfile: UserProfile = userProfileList) {
    Scaffold(topBar = {
        AppBar(
            title = "User profile details",
            icon = Icons.Default.ArrowBack
        ) {
            navController?.navigateUp()
        }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.padding(top = 100.dp))
                ProfilePicture(userProfile.pictureUrl, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    MyTheme {
        UserProfileDetailsScreen(userId = 0, null)
    }
}


@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    MyTheme {
        //UserListScreen()
        UserListScreen(userProfiles = userProfileList, null)
    }
}