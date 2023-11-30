package com.example.contactdetails.screens.contactscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contactdetails.R
import com.example.contactdetails.screens.ContactPicture

@Composable
fun ContactDetailsCard(contactId: Int, navigateUp: () -> Unit) {
    val contactDetail = contactDetailList.first{ detail -> contactId == detail.id }
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(color = Color(0xFF325CBC))
                    .padding(16.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier.clickable(onClick = navigateUp),
                    tint = Color.White
                )
                Text(text = "Edit Contact", fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (70).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            DisplayContactDetails(contactDetail)
        }
    }
}


@Composable
fun DisplayContactDetails(detail: ContactDetail){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ContactPicture(detail.imgSrc, 100.dp)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = detail.name,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 3.dp)
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CreateOutlinedIconButton("Message", R.drawable.clock_icon)
        CreateOutlinedIconButton("Call", R.drawable.clock_icon)
        CreateOutlinedIconButton("Video", R.drawable.clock_icon)
    }
    Row( modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, start = 16.dp, end = 16.dp),
    ) {
        CreateOutlinedTextButton(detail.contactNo, detail.country)
    }

    CreateOutlinedTextIcon("Whatsapp", R.drawable.clock_icon)
    CreateOutlinedTextIcon("Telegram", R.drawable.clock_icon )
    CreateTextButton("Add to Favourites", Color.DarkGray)
    CreateTextButton("Block this Number", Color.Red)

}

@Composable
fun CreateOutlinedIconButton(iconDescription: String, showIcon: Int){

    OutlinedButton(
        onClick = {},
        shape = RoundedCornerShape(7.dp),
        border = BorderStroke(1.5.dp, Color.LightGray),
        modifier = Modifier.padding(2.5.dp)
    ) {
        Column(
            modifier = Modifier
                .size(width = 60.dp, height = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = showIcon),
                contentDescription = iconDescription,
                modifier = Modifier.size(20.dp),
                tint = Color(0xFF325CBC)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(
                text = iconDescription,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF325CBC)
            )
        }
    }
}

@Composable
fun CreateOutlinedTextButton(mobileNo: String, country: String) {
    OutlinedButton(
        onClick = {},
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier.height(height = 65.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Mobile | $country",
                color = Color.Gray,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold)
            Text(
                text = mobileNo,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.padding(top=8.dp))
        }
    }
}

@Composable
fun CreateOutlinedTextIcon(content: String, showIcon: Int){
    OutlinedButton(
        onClick = {},
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .height(height = 80.dp)
            .background(Color(0xFFFAFBFD))
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = content, fontWeight = FontWeight.Bold, color = Color.Black)
            Image(
                painter = painterResource(id = showIcon),
                contentDescription = content,
                modifier = Modifier.size(20.dp)
            )

        }
    }
}

@Composable
fun CreateTextButton(leadText: String, textColor: Color){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.Top,
    ){
        Text(text = leadText, color = textColor, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
    }
}

@Composable
@Preview(showBackground = true)
fun ContactDetailsScreenPreview() {
    ContactDetailsCard(contactId = 0) {}
}