package com.example.mvvm.fragments

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.repository.Repository
import com.sample.mvvmlivedataroomdattbinding.models.UserModel
import kotlinx.coroutines.*
import org.app.mdta.Interface.CallbackResponse

class UsersListViewModel(
    private val mContext: Context
) : ViewModel() {

    /*data that will fetch asynchronously*/
    val usersList: MutableLiveData<List<UserModel>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val noData: MutableLiveData<Boolean> = MutableLiveData()
    private var usersListViewModelJob = Job()
    private var ioScope = CoroutineScope(Dispatchers.IO + usersListViewModelJob)


    init {
        usersList.value = emptyList()
        loading.value = true
        noData.value = false
    }


    /*fetches data asynchronously with coroutines scope to avoid blocking thread*/
    fun fetchUsers() {
        ioScope.launch {
            Repository(mContext).getAllUsers(object : CallbackResponse {
                override fun <T> onSuccess(response: T) {
                    ioScope.launch {
                        withContext(Dispatchers.Main){
                            /*gets result from UserModel*/
                            val result = response as List<UserModel>
                            loading.value = false
                            if (result.isEmpty()){
                                noData.value = true
                            }else{
                                usersList.value = result
                            }
                        }
                    }
                }

                /*stop progressbar from loading*/
                override fun <T> onFailure(response: T) {
                    loading.value = false
                    Toast.makeText(mContext,response as String,Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
