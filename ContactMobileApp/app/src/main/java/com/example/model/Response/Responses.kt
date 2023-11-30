package com.example.model.Response

import com.google.gson.annotations.SerializedName

data class ContactDetailsResponse(val contacts: List<ContactResponse>)


data class ContactResponse (
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory") val name: String,
    @SerializedName("StrCategory") val phoneNo: String,
    @SerializedName("strCategory") val country: String,
    @SerializedName("StrCategoryThumb") val imageUrl: String

    )