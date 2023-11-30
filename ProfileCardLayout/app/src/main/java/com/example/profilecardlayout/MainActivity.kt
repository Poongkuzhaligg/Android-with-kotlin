package com.example.profilecardlayout

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.profilecardlayout.ui.theme.MyTheme
import com.example.profilecardlayout.ui.theme.lightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list"){
            UserListScreen(userProfiles, navController)
        }
        composable(
            route = "users_details/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"))
        }
    }
}

@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavHostController? ) {
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ){
            LazyColumn{
                items(userProfiles) { userProfile ->
                    ProfileCard(userProfile = userProfile) {
                        navController?.navigate("users_details/${userProfile.id}")
                    }
                }
            }
            
        }
    }
}

@Composable
fun UserProfileDetailsScreen(userId: Int) {
    val userProfile = userProfileList.first{ userProfile -> userId == userProfile.id }
    Scaffold(topBar = { AppBar() }) {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(userProfile.drawableId, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
            }
        }
    }
}

@Composable 
fun AppBar() {
    TopAppBar(
        navigationIcon = { Icon(
            Icons.Default.Home,
            "content description",
            modifier = Modifier.padding(horizontal = 12.dp)
        ) },
        title = { Text(text = "Messaging Application Users")  }
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = { clickAction.invoke() }),
        elevation = 8.dp,
        backgroundColor = Color.White
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(userProfile.drawableId, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }

    }
}

@Composable
fun ProfilePicture(drawableId: Int, onlineStatus: Boolean, imageSize: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 3.dp,
            color = if(onlineStatus)
                MaterialTheme.colors.lightGreen
        else Color.Red
        ),
        modifier = Modifier
            .padding(16.dp)
            .size(imageSize),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = null,
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Crop,
        )
    }

}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(LocalContentAlpha provides(ContentAlpha.medium)
        ) {
            Text(
                text = userName,
                style = MaterialTheme.typography.h5
            )
        }
        CompositionLocalProvider(LocalContentAlpha provides(ContentAlpha.medium)) {
            Text(
                text = if(onlineStatus)
                    "Active now"
                else "Offline",
                style = MaterialTheme.typography.body2
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    MyTheme {
        UserProfileDetailsScreen(userId = 0)
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    MyTheme {
        UserListScreen(userProfiles = userProfileList, null)
    }
}