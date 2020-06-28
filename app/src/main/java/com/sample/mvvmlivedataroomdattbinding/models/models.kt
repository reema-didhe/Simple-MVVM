package com.sample.mvvmlivedataroomdattbinding.models

import com.example.mvvm.database.User

data class UserModel(var title: String, var url: String)

fun List<UserModel>.toUser(): List<User> {
    var users = ArrayList<User>()
    this.forEach {
        var user = User(it.title, url = it.url)
        users.add(user)
    }
    return users
}

fun List<User>.toUserModel(): List<UserModel> {
    var users = ArrayList<UserModel>()
    this.forEach {
        var user = UserModel(it.title, url = it.url)
        users.add(user)
    }
    return users
}