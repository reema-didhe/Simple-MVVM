package com.example.mvvm.repository

import android.content.Context
import android.graphics.Bitmap

class DownloadImage(val context: Context){

    /*pass the image base url and image name*/
    suspend fun loadImage(url: String):Bitmap?{
        return Repository(context).downloadImageFromUrl(url)
    }

}