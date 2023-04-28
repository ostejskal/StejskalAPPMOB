package com.example.stejskalappmob

import com.google.gson.annotations.SerializedName

class Response {
    @SerializedName("short_description")
    var short_description: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("thumbnail")
    var thumbnail: String? = null
    @SerializedName("game_url")
    var game_url: String? = null
    @SerializedName("genre")
    var genre: String? = null
    @SerializedName("platform")
    var platform: String? = null
    @SerializedName("publisher")
    var publisher: String? = null
    @SerializedName("developer")
    var developer: String? = null
    @SerializedName("release_date")
    var release_date: String? = null
    @SerializedName("freetogame_profile_url")
    var freetogame_profile_url: String? = null
}