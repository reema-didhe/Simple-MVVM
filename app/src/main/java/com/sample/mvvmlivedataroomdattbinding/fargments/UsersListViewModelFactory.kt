package com.android.notebook.fragment.notelist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm.fragments.UsersListViewModel

class UsersListViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory{

   /*pass instance to ViewModelProvider to use UsersListViewModel*/
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            return UsersListViewModel(mContext =  application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}