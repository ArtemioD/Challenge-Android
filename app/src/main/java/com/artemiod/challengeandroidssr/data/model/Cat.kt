package com.artemiod.challengeandroidssr.data.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("id") val id: String,
    @SerializedName("url") val url_img: String,
)
