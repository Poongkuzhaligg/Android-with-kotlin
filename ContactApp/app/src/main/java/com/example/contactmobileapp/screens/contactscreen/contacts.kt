package com.example.contactdetails.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.contactdetails.screens.contactscreen.ContactDetail
import com.example.contactdetails.screens.contactscreen.ContactDetailsCard
import com.example.contactdetails.screens.contactscreen.contactDetailList

@Composable
fun ContactsScreen(contactDetails: List<ContactDetail> = contactDetailList) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "contacts_list") {
            composable("contacts_list"){
                ContactListsDisplay(contactDetails, navController)
            }
            composable(
                route = "Contact_details/{contactId}",
                arguments = listOf(navArgument("contactId"){
                    type = NavType.IntType
                })
            ) { navBackStackEntry ->
                ContactDetailsCard(navBackStackEntry.arguments!!.getInt("contactId"))
                { navController.navigateUp() }
            }
        }
    }
}

@Composable
fun ContactListsDisplay(contactDetails: List<ContactDetail>, navController: NavHostController?){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Contact", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Add Contact"
                , fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF325CBC))
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, start = 8.dp, end = 8.dp)
        ) {
            LazyColumn {
                items(contactDetails) { contactDetail ->
                    ContactList(detail = contactDetail) {
                        navController?.navigate("contact_details/${contactDetail.id}")
                    }
                }
            }
        }
    }
}


@Composable
fun ContactList(detail: ContactDetail, clickAction: () -> Unit){
    Card(
        shape= RectangleShape ,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = { clickAction.invoke() })
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ContactPicture(detail.imgSrc, 55.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                    Text(
                        text = detail.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(text = detail.contactNo, color = Color.Gray, fontSize = 12.sp)
              }
        }
        Divider(color = Color.LightGray)

    }
}

@Composable
fun ContactPicture(contactImg: Int, imageSize: Dp){
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = Color.DarkGray
        ),
        modifier = Modifier
            .padding(16.dp)
            .size(imageSize),
        elevation = 4.dp
    ) {
        Image(
            painter = painterResource(id = contactImg),
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
    }
}

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit,
){

}

@Composable
@Preview(showBackground = true)
fun ContactScreenPreview() {
    ContactsScreen()
}
