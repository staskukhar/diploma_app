package com.example.mypets

import android.net.Uri
import java.net.URL

data class MediaContent(val title: String, val url: String) {
    companion object{
        val content = listOf<MediaContent>(
            MediaContent("Care of pets", "https://www.youtube.com/watch?v=SOmZckDIPOY"),
            MediaContent("Walk with pets", "https://youtu.be/oNNsQuf2ayo")


        )
    }
}