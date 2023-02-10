package com.example.mypets

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.mypets.databinding.MediaItemBinding
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class MediaAdapter() : RecyclerView.Adapter<MediaAdapter.MediaHolder>() {
    private val mediaList = ArrayList<MediaContent>()
    class MediaHolder(item: View, context: Context) : RecyclerView.ViewHolder(item) {
        val binding = MediaItemBinding.bind(item)
        val _context = context

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetJavaScriptEnabled")
        fun bind(media: MediaContent){
            binding.apply {
                wvMedia.apply {
                    webViewClient = WebViewClient()
                    loadUrl(media.url)
                    settings.javaScriptEnabled = true
                    settings.safeBrowsingEnabled = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
        return MediaHolder(view, parent.context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MediaHolder, position: Int) {
        holder.bind(mediaList[position])
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    fun addMedia(pets: List<MediaContent>){
        pets.forEach(){
            mediaList.add(it)
        }
    }
    fun mediaClear(){
        mediaList.clear()
    }
}