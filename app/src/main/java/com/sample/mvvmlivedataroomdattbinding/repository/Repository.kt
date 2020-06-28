package com.example.mvvm.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.mvvm.database.AppDatabase
import com.sample.mvvmlivedataroomdattbinding.models.toUserModel
import org.app.mdta.Interface.CallbackResponse

class Repository {

    var database: AppDatabase
    var context: Context

    constructor(context: Context) {
        this.context = context
        this.database = AppDatabase.getInstance(context)
    }

    /*Gets data from network if internet is available otherwise from local storage*/
    fun getAllUsers(callbackResponse: CallbackResponse) {
        if (Utils.isNetworkAvailable(context)) {
            Network(context).getAllUsers(object : CallbackResponse {
                override fun <T> onSuccess(response: T) {
                    callbackResponse.onSuccess(response)
                }

                override fun <T> onFailure(response: T) {
                    callbackResponse.onFailure(response)
                }
            })
        }else{
            callbackResponse.onSuccess(database.usersDao.getAllUsers().toUserModel())
        }
    }

    suspend fun downloadImageFromUrl(imageUrl:String):Bitmap? {
        if (Utils.isNetworkAvailable(context)) {
          return Network(context).downloadImageFromUrl(imageUrl)
        }else{
            return null
        }
    }


}