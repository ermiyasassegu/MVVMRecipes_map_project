package com.mvvmrecipesmap_project.util


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri


fun watchYoutubeVideo(context: Context, url: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    try {
        context.startActivity(intent)
    } catch (ex: ActivityNotFoundException) {
    }
}