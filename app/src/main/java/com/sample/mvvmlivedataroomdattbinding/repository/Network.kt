package com.example.mvvm.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.mvvm.database.AppDatabase
import com.example.mvvm.remote.ApiInterface
import com.example.mvvm.remote.Retrofit
import com.sample.mvvmlivedataroomdattbinding.models.UserModel
import com.sample.mvvmlivedataroomdattbinding.models.toUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.app.mdta.Interface.CallbackResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class Network {
    var database: AppDatabase
    constructor(context: Context) {
        database = AppDatabase.getInstance(context)
    }

    fun getAllUsers(callbackResponse: CallbackResponse) {
        val service = Retrofit.getInstance().create(ApiInterface::class.java)
        val call = service.getUsers()
        call.enqueue(object : Callback<List<UserModel>> {
            override fun onFailure(call: Call<List<UserModel>>?, t: Throwable?) {
                callbackResponse.onFailure(t!!.message)
            }

            override fun onResponse(
                call: Call<List<UserModel>>?,
                response: Response<List<UserModel>>?
            ) {
                val response = response?.body()
                if (response != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (response.isNotEmpty()) {
                            database.usersDao.insert(response.toUser())
                        }
                    }

                    callbackResponse.onSuccess(response)
                }else{
                    callbackResponse.onFailure("Failure")
                }
            }
        })
    }

    /*downloads the image from image url*/
    @Throws(IOException::class)
    suspend fun downloadImageFromUrl(strUrl: String): Bitmap? {

        var bitmap: Bitmap? = null
        var iStream: InputStream? = null
        try {
            val url = URL(strUrl)
            val urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.connect()
            iStream = urlConnection.getInputStream()
            bitmap = BitmapFactory.decodeStream(iStream)

        } catch (e: Exception) {
            Log.d("Exception while downloading url", e.toString())
        } finally {
            iStream?.close()
        }
        return bitmap
    }
}